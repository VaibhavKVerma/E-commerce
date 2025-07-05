package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.dto.OrderResponseDto;
import com.ecommerce.orderservice.enums.OrderStatus;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrder(Long id);
    OrderResponseDto updateOrder(Long id, OrderStatus status);
}
