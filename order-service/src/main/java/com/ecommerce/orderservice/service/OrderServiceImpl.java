package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.dto.OrderResponseDto;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.enums.OrderStatus;
import com.ecommerce.orderservice.kafka.KafkaProducerService;
import com.ecommerce.orderservice.mapper.OrderMapper;
import com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        double amount = orderRequestDto
                .getProducts()
                .stream()
                .mapToDouble(product -> product.getAmount() * product.getQuantity())
                .sum();
        BigDecimal roundedAmount = BigDecimal.valueOf(amount).setScale(3, RoundingMode.HALF_UP);
        orderRequestDto.setAmount(roundedAmount.doubleValue());
        Order order = orderRepository.save(OrderMapper.toObject(orderRequestDto));
        kafkaProducerService.sendOrderCreatedEvent(order);
        return OrderMapper.toDto(order);
    }

    @Override
    public OrderResponseDto getOrder(Long id) {
        Order order = orderRepository.findByIdWithProducts(id).orElse(null);
        if (order == null) {
            return null;
        }
        return OrderMapper.toDto(order);
    }

    @Override
    public OrderResponseDto updateOrder(Long id, OrderStatus status) {
        return null;
    }
}

/*
*
* OrderStateContext
*
*
* */
