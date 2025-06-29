package com.ecommerce.orderservice.config;

import io.grpc.ManagedChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;
import productcataloggrpc.ProductCatalogServiceGrpc;

@Configuration
public class ProductCatalogGrpcConfig {
    @Bean
    public ProductCatalogServiceGrpc.ProductCatalogServiceBlockingStub productCatalogServiceBlockingStub(GrpcChannelFactory channels) {
        ManagedChannel channel = channels.createChannel("product-catalog-service");
        System.out.println("Bean Created for channel : " + channel);
        return ProductCatalogServiceGrpc.newBlockingStub(channel);
    }
}
