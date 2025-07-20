package com.ecommerce.inventoryservice.service;

import inventorygrpc.GetItemsRequest;
import inventorygrpc.InventoryServiceGrpc;
import inventorygrpc.ItemResponse;
import inventorygrpc.ItemsResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class InventoryServiceGrpcImpl extends InventoryServiceGrpc.InventoryServiceImplBase {
    @Override
    public void getItemsStock(GetItemsRequest request, StreamObserver<ItemsResponse> responseObserver) {
        List<ItemResponse> items = request.getItemsList()
                .stream()
                .map(item ->
                        ItemResponse.newBuilder()
                                .setId(item.getId())
                                .setQuantity(Integer.MAX_VALUE)
                                .build()
                ).toList();

        ItemsResponse itemsResponse = ItemsResponse.newBuilder()
                .addAllItems(items)
                .build();
        responseObserver.onNext(itemsResponse);
        responseObserver.onCompleted();
    }
}
