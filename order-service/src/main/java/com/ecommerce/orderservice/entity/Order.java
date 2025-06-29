package com.ecommerce.orderservice.entity;

import com.ecommerce.orderservice.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
    private List<Product> products;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private Instant createdAt;

    private Instant completedAt;
    private Instant failedAt;
    private Instant refundedAt;
    private Instant cancelledAt;
    private Instant updatedAt;
}
