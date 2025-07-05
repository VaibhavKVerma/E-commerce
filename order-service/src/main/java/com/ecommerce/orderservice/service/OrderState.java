package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.entity.Order;

public interface OrderState {
    Order created();
    Order pending();
    Order cancelled();
    Order failed();
    Order completed();
    Order refunded();
}
