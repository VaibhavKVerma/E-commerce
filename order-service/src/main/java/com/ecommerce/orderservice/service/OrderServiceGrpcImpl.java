package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.ItemsInventoryRequest;
import com.ecommerce.orderservice.dto.ItemsInventoryResponse;
import com.ecommerce.orderservice.dto.OrderResponseDto;
import com.ecommerce.orderservice.entity.Product;
import com.ecommerce.orderservice.grpc.InventoryGrpcClient;
import com.ecommerce.orderservice.grpc.ProductCatalogGrpcClient;
import com.ecommerce.orderservice.mapper.OrderGrpcMapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import ordergrpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;

@GrpcService
public class OrderServiceGrpcImpl extends OrderServiceGrpc.OrderServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceGrpcImpl.class);
    private final ProductCatalogGrpcClient productCatalogGrpcClient;
    private final InventoryGrpcClient inventoryGrpcClient;
    private final OrderService orderService;
    private final Executor productCatalogAsyncExecutor;

    @Autowired
    public OrderServiceGrpcImpl(ProductCatalogGrpcClient productCatalogGrpcClient,
                                InventoryGrpcClient inventoryGrpcClient,
                                OrderService orderService,
                                @Qualifier("ProductCatalogAsyncExecutor") Executor productCatalogAsyncExecutor) {
        this.orderService = orderService;
        this.productCatalogGrpcClient = productCatalogGrpcClient;
        this.productCatalogAsyncExecutor = productCatalogAsyncExecutor;
        this.inventoryGrpcClient = inventoryGrpcClient;
    }

    private Product fetchProductDetails(String productId, int quantity) {
        log.info("Fetching product details for ID: {}", productId);
        Product product = productCatalogGrpcClient.getProductDetails(productId);

        if (product == null) {
            throw new CompletionException(
                    Status.NOT_FOUND
                            .withDescription("Product not found: " + productId)
                            .asRuntimeException()
            );
        }

        product.setQuantity(quantity);
        return product;
    }

    @Override
    public void createOrder(CreateOrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        List<CompletableFuture<Product>> futures = new ArrayList<>();
        for (var productRequest : request.getProductsList()) {
            CompletableFuture<Product> future = CompletableFuture.supplyAsync(() ->
                            fetchProductDetails(productRequest.getProductId(), Integer.parseInt(productRequest.getProductQuantity())),
                    productCatalogAsyncExecutor
            );
            futures.add(future);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        allFutures.whenComplete((v, throwable) -> {
            if (throwable != null) {
                responseObserver.onError(Status.NOT_FOUND.withDescription(throwable.getMessage()).asException());
            } else {
                List<Product> products = futures.stream().map(CompletableFuture::join).toList();
                if (products.isEmpty()) {
                    log.error("Invalid Order Request");
                    responseObserver.onError(Status.INVALID_ARGUMENT
                            .withDescription("Invalid Order Request")
                            .asRuntimeException());
                    return;
                }

                ItemsInventoryResponse inventoryResponse = this.inventoryGrpcClient.getItemsStock(ItemsInventoryRequest
                        .builder()
                        .productList(products)
                        .build());

                if(inventoryResponse.getProductList().size() != products.size()) {
                    log.error("All Items unavailable right now");
                    responseObserver.onError(Status.NOT_FOUND.withDescription("All Items Unavailable").asRuntimeException());
                }

                OrderResponseDto response = orderService.createOrder(OrderGrpcMapper.toDto(request, products));
                responseObserver.onNext(OrderGrpcMapper.toResponse(response));
                responseObserver.onCompleted();
            }
        });
    }

    @Override
    public void getOrderDetails(GetOrderDetailsRequest request, StreamObserver<OrderResponse> responseObserver) {
        Long orderId = Long.valueOf(request.getOrderId());
        OrderResponseDto response = orderService.getOrder(orderId);
        if (response == null) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Order with id " + orderId + " not found.").asException());
            return;
        }
        responseObserver.onNext(OrderGrpcMapper.toResponse(response));
        responseObserver.onCompleted();
    }

    @Override
    public void updateOrderStatus(UpdateOrderStatusRequest request, StreamObserver<OrderResponse> responseObserver) {

    }
}
