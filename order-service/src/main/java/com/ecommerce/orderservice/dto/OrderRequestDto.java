package com.ecommerce.orderservice.dto;

import com.ecommerce.orderservice.entity.Product;
import com.ecommerce.orderservice.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrderRequestDto {
    private Long id;
    private List<Product> products;
    private Long userId;
    private Double amount;
    private OrderStatus status;
}
