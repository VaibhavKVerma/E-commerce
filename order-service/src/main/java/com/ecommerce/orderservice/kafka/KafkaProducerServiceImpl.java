package com.ecommerce.orderservice.kafka;


import com.ecommerce.orderservice.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private <T> void sendMessage(String topic, String key, T message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, message);
        future.whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.info("Message {} sent for topic {} and key {} successfully.", message, result.getRecordMetadata().topic(), key);
            } else {
                log.error("Message {} for topic {} and key {} failed.", message, result.getRecordMetadata().topic(), key);
            }
        });
    }

    private <T> void sendMessage(String topic, T message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.info("Message {} sent for topic {} successfully.", message, result.getRecordMetadata().topic());
            } else {
                log.error("Message {} for topic {} failed.", message, result.getRecordMetadata().topic());
            }
        });
    }

    @Override
    public void sendOrderCreatedEvent(Order order) {
        sendMessage("order-created-event", order);
    }

    @Override
    public void sendOrderCompletedEvent(Order order) {
        sendMessage("order-completed-event", order);
    }

    @Override
    public void sendOrderFailedEvent(Order order) {
        sendMessage("order-failed-event", order);
    }

    @Override
    public void sendOrderRefundedEvent(Order order) {
        sendMessage("order-refunded-event", order);
    }

    @Override
    public void sendOrderCancelledEvent(Order order) {
        sendMessage("order-cancelled-event", order);
    }
}
