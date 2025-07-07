package com.ecommerce.orderservice.dto;

import com.ecommerce.orderservice.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ItemsInventoryResponse {
    private List<Product> productList;
}
