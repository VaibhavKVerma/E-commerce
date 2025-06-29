package com.ecommerce.orderservice.grpc;

import com.ecommerce.orderservice.entity.Product;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import productcataloggrpc.GetProductRequest;
import productcataloggrpc.ProductCatalogServiceGrpc.ProductCatalogServiceBlockingStub;
import productcataloggrpc.ProductResponse;

@Service
public class ProductCatalogGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(ProductCatalogGrpcClient.class);
    @GrpcClient("productCatalogService")
    private ProductCatalogServiceBlockingStub blockingStub;

    Product getProductDetails(String productId) {
        GetProductRequest request = GetProductRequest.newBuilder().setProductId(productId).build();
        try {
            ProductResponse response = blockingStub.getProduct(request);

            return Product
                    .builder()
                    .id(response.getProductId())
                    .category(response.getProductCategory())
                    .description(response.getProductDescription())
                    .amount(response.getProductPrice())
                    .build();
        } catch (Exception e) {
            log.error("No such product exist in catalog ID : {}", productId, e);
            return null;
        }
    }
}
