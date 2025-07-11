package com.ecommerce.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String,Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        map.put(ProducerConfig.CLIENT_ID_CONFIG, "orderservice");
        return new DefaultKafkaProducerFactory<>(map);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic orderCreatedEventTopic() {
        return TopicBuilder
                .name("order-created-event")
                .partitions(4)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderCompletedEventTopic() {
        return TopicBuilder
                .name("order-completed-event")
                .partitions(4)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderFailedEventTopic() {
        return TopicBuilder
                .name("order-failed-event")
                .partitions(4)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderRefundedEventTopic() {
        return TopicBuilder
                .name("order-refunded-event")
                .partitions(4)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderCancelledEventTopic() {
        return TopicBuilder
                .name("order-cancelled-event")
                .partitions(4)
                .replicas(1)
                .build();
    }
}
