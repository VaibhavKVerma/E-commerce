package com.ecommerce.orderservice.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    String id;
    Integer quantity;
    String description;
    String category;
    Double amount;
}
