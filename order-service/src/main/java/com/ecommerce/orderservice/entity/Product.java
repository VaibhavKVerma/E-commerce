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
    String name;
    Integer quantity;
    String description;
    String category;
    Double amount;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
