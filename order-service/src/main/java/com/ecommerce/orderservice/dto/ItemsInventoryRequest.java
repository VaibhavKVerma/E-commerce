package com.ecommerce.orderservice.dto;

import com.ecommerce.orderservice.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class ItemsInventoryRequest {
    private List<Product> productList;
}
