package com.ecommerce.productcatalogservice.service;

import com.ecommerce.productcatalogservice.entity.Product;
import com.ecommerce.productcatalogservice.mapper.ProductMapper;
import com.ecommerce.productcatalogservice.repository.ProductRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;
import productcataloggrpc.CreateProductRequest;
import productcataloggrpc.DeleteProductRequest;
import productcataloggrpc.Empty;
import productcataloggrpc.GetProductRequest;
import productcataloggrpc.ProductCatalogServiceGrpc;
import productcataloggrpc.ProductResponse;
import productcataloggrpc.UpdateProductRequest;

@GrpcService
public class ProductCatalogGrpcServiceImpl extends ProductCatalogServiceGrpc.ProductCatalogServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(ProductCatalogGrpcServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductCatalogGrpcServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private <T> Product findByIdAndThrow(String id, StreamObserver<T> responseObserver) {
        Long productId = null;
        try {
            productId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Invalid product ID format: " + id)
                    .asException());
            return null;
        }

        Product product = this.productRepository.findByIdAndRecordStatus(productId, true);

        if (product == null) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("No such Product Exists for this ID : "+ id).asException());
            return null;
        }
        return product;
    }

    @Override
    public void createProduct(CreateProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        String productName = request.getProductName();

        if (this.productRepository.findByNameAndRecordStatus(productName, true) != null) {
            responseObserver.onError(Status.ALREADY_EXISTS.asException());
            return;
        }

        Product product = this.productRepository.save(ProductMapper.convertToEntity(request));
        responseObserver.onNext(ProductMapper.convertToResponse(product));
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(GetProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product product = findByIdAndThrow(request.getProductId(), responseObserver);
        responseObserver.onNext(ProductMapper.convertToResponse(product));
        responseObserver.onCompleted();
    }

    @Override
    public void updateProduct(UpdateProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product existingProduct = findByIdAndThrow(request.getProductId(), responseObserver);
        Product updatedProduct = ProductMapper.convertToEntityForUpdate(existingProduct,request);
        Product product = this.productRepository.save(updatedProduct);
        responseObserver.onNext(ProductMapper.convertToResponse(product));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProduct(DeleteProductRequest request, StreamObserver<Empty> responseObserver) {
        Product existingProduct = findByIdAndThrow(request.getProductId(), responseObserver);

        existingProduct.setRecordStatus(false);
        this.productRepository.save(existingProduct);

        log.info("Product has been deleted Id: {}", request.getProductId());
        responseObserver.onNext(productcataloggrpc.Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
