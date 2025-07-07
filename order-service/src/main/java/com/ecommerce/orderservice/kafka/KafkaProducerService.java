package com.ecommerce.orderservice.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendTest(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic", message);
        future.whenComplete((result, throwable)-> {
            if(throwable != null) {
                log.info("Message {} sent for topic {} succesfully.",message, result.getRecordMetadata().topic());
            } else {
                log.error("Message {} for topic {} failed.",message, result.getRecordMetadata().topic());
            }
        });
    }
}
