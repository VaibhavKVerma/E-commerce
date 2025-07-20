package com.ecommerce.inventoryservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    @KafkaListener(topics = "order-created-event", groupId = "order-service")
    public void consumeOrderCreatedEvent(Order order) {

    }
}
