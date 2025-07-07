package com.ecommerce.orderservice.config;

import inventorygrpc.InventoryServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class InventoryGrpcConfig {
    @Bean
    public InventoryServiceGrpc.InventoryServiceBlockingStub inventoryServiceBlockingStub(GrpcChannelFactory channels) {
        ManagedChannel channel = channels.createChannel("inventory-service");
        System.out.println("Bean Created for channel : " + channel);
        return InventoryServiceGrpc.newBlockingStub(channel);
    }
}
