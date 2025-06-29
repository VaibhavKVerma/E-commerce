package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.entity.Product;
import com.ecommerce.orderservice.grpc.ProductCatalogGrpcClient;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import ordergrpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class OrderServiceGrpcImpl extends OrderServiceGrpc.OrderServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceGrpcImpl.class);
    private final ProductCatalogGrpcClient productCatalogGrpcClient;

    @Override
    public void createOrder(CreateOrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        List<Product> products = request
                .getProductsList()
                .stream()
                .map(product -> productCatalogGrpcClient.getProductDetails(product.getProductId()))
                .toList();

        products.forEach(product ->
                log.info("Product info : {}", product.getName()));

        responseObserver.onCompleted();
    }

    @Override
    public void getOrderDetails(GetOrderDetailsRequest request, StreamObserver<OrderResponse> responseObserver) {

    }

    @Override
    public void updateOrderStatus(UpdateOrderStatusRequest request, StreamObserver<OrderResponse> responseObserver) {

    }
}
