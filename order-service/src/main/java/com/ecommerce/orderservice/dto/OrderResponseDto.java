package com.ecommerce.orderservice.dto;

import com.ecommerce.orderservice.entity.Product;
import com.ecommerce.orderservice.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Builder
@Setter
@Getter
public class OrderResponseDto {
    private Long id;
    private List<Product> products;
    private Long userId;
    private double amount;
    private OrderStatus status;
    private Instant createdAt;
}
