package com.ecommerce.orderservice.service;


import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.dto.OrderResponseDto;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.mapper.OrderMapper;
import com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderRepository.save(OrderMapper.toObject(orderRequestDto));
        return OrderMapper.toDto(order);
    }
}
