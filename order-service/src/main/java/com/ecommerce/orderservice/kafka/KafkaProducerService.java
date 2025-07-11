package com.ecommerce.orderservice.kafka;

import com.ecommerce.orderservice.entity.Order;

public interface KafkaProducerService {
    void sendOrderCreatedEvent(Order order);
    void sendOrderCompletedEvent(Order order);
    void sendOrderFailedEvent(Order order);
    void sendOrderRefundedEvent(Order order);
    void sendOrderCancelledEvent(Order order);
}
