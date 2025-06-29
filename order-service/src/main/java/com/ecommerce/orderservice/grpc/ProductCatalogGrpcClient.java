package com.ecommerce.orderservice.grpc;

import com.ecommerce.orderservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import productcataloggrpc.GetProductRequest;
import productcataloggrpc.ProductCatalogServiceGrpc;
import productcataloggrpc.ProductResponse;

@Service
@RequiredArgsConstructor
public class ProductCatalogGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(ProductCatalogGrpcClient.class);
    private final ProductCatalogServiceGrpc.ProductCatalogServiceBlockingStub blockingStub;

    public Product getProductDetails(String productId) {
        GetProductRequest request = GetProductRequest.newBuilder().setProductId(productId).build();
        try {
            ProductResponse response = blockingStub.getProduct(request);

            return Product
                    .builder()
                    .id(response.getProductId())
                    .category(response.getProductCategory())
                    .description(response.getProductDescription())
                    .amount(response.getProductPrice())
                    .name(response.getProductName())
                    .build();
        } catch (Exception e) {
            log.error("No such product exist in catalog ID : {}", productId, e);
            return null;
        }
    }
}
