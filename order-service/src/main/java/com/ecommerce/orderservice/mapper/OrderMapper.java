package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.dto.OrderResponseDto;
import com.ecommerce.orderservice.entity.Order;

import java.time.Instant;

public class OrderMapper {
    public static OrderResponseDto toDto(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .products(order.getProducts())
                .status(order.getStatus())
                .amount(order.getAmount())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static Order toObject(OrderRequestDto orderRequestDto) {
        return Order.builder()
                .id(orderRequestDto.getId())
                .userId(orderRequestDto.getUserId())
                .products(orderRequestDto.getProducts())
                .status(orderRequestDto.getStatus())
                .amount(orderRequestDto.getAmount())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}
