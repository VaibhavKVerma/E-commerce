package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.dto.OrderResponseDto;
import com.ecommerce.orderservice.entity.Product;
import com.ecommerce.orderservice.enums.OrderStatus;
import ordergrpc.CreateOrderRequest;
import ordergrpc.OrderResponse;
import ordergrpc.ProductInfo;

import java.util.List;

public class OrderGrpcMapper {
    public static OrderResponse toResponse(OrderResponseDto responseDto) {
        OrderResponse.Builder orderResponseBuilder = OrderResponse.newBuilder();
        orderResponseBuilder.setOrderId(responseDto.getId().toString())
                .setStatus(responseDto.getStatus().name())
                .setUserId(responseDto.getUserId().toString());

        for (Product product : responseDto.getProducts()) {
            ProductInfo productInfo = ProductInfo.newBuilder()
                    .setProductId(product.getId())
                    .setProductCategory(product.getCategory())
                    .setProductQuantity(product.getQuantity().toString())
                    .setProductDescription(product.getDescription())
                    .build();
            orderResponseBuilder.addProducts(productInfo);
        }

        return orderResponseBuilder.build();
    }

    public static OrderRequestDto toDto(CreateOrderRequest request, List<Product> products) {
        double amount = products.stream().mapToDouble(Product::getAmount).sum();
        return OrderRequestDto.builder()
                .userId(Long.valueOf(request.getUserId()))
                .status(OrderStatus.CREATED)
                .products(products)
                .amount(amount)
                .build();
    }
}
