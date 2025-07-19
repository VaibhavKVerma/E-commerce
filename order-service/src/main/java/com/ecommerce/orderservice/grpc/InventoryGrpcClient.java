package com.ecommerce.orderservice.grpc;

import com.ecommerce.orderservice.dto.ItemsInventoryRequest;
import com.ecommerce.orderservice.dto.ItemsInventoryResponse;
import com.ecommerce.orderservice.entity.Product;
import inventorygrpc.GetItemRequest;
import inventorygrpc.GetItemsRequest;
import inventorygrpc.InventoryServiceGrpc;
import inventorygrpc.ItemsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryGrpcClient {
    private final InventoryServiceGrpc.InventoryServiceBlockingStub inventoryServiceBlockingStub;

    public ItemsInventoryResponse getItemsStock(ItemsInventoryRequest request) {
        List<GetItemRequest> list = request.getProductList().stream()
                .map(item -> GetItemRequest
                        .newBuilder()
                        .setId(item.getId())
                        .build())
                .toList();

        GetItemsRequest getItemsRequest = GetItemsRequest.newBuilder()
                .addAllItems(list)
                .build();
        ItemsResponse itemsResponse = inventoryServiceBlockingStub.getItemsStock(getItemsRequest);

        List<Product> responseList = new ArrayList<>();
        for (int i = 0; i < itemsResponse.getItemsList().size(); i++) {
            Product product = request.getProductList().get(i);
            product.setQuantity(itemsResponse.getItems(i).getQuantity());
            responseList.add(product);
        }

        return ItemsInventoryResponse.builder().productList(responseList).build();
    }

}
