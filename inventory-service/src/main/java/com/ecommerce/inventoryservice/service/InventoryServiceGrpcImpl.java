package com.ecommerce.inventoryservice.service;

import inventorygrpc.*;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class InventoryServiceGrpcImpl extends InventoryServiceGrpc.InventoryServiceImplBase {
    @Override
    public void getItemInventory(ItemRequest request, StreamObserver<ItemResponse> responseObserver) {
        ItemResponse itemResponse = ItemResponse.newBuilder()
                .setId(request.getId())
                .setQuantity(Integer.MAX_VALUE)
                .build();
        responseObserver.onNext(itemResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getItemsInventory(ItemsRequest request, StreamObserver<ItemsResponse> responseObserver) {
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
