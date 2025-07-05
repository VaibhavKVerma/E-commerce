package com.ecommerce.productcatalogservice.mapper;

import com.ecommerce.productcatalogservice.entity.Product;
import productcataloggrpc.CreateProductRequest;
import productcataloggrpc.ProductResponse;

import java.time.Instant;

public class ProductMapper {

    public static Product convertToEntity(CreateProductRequest request) {
        return Product
                .builder()
                .name(request.getProductName().toLowerCase())
                .description(request.getProductDescription())
                .category(request.getProductCategory())
                .price(request.getProductPrice())
                .recordStatus(true)
                .updatedAt(Instant.now())
                .createdAt(Instant.now())
                .build();
    }

    public static Product convertToEntityForUpdate(Product existingProduct, productcataloggrpc.UpdateProductRequest request) {
        existingProduct.setDescription(request.getProductDescription());
        existingProduct.setCategory(request.getProductCategory());
        if (!request.getProductCategory().trim().isEmpty()) {
            existingProduct.setCategory(request.getProductCategory());
        }
        if (request.getProductPrice() > 0) {
            existingProduct.setPrice(request.getProductPrice());
        }
        existingProduct.setUpdatedAt(Instant.now());
        return existingProduct;
    }

    public static ProductResponse convertToResponse(Product product) {
        return ProductResponse
                .newBuilder()
                .setProductName(product.getName())
                .setProductDescription(product.getDescription())
                .setProductCategory(product.getCategory())
                .setProductId(product.getId().toString())
                .setProductPrice(product.getPrice())
                .build();
    }
}
