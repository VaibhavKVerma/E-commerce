package com.ecommerce.orderservice.service.statehandler;

import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.service.OrderState;
import org.springframework.stereotype.Component;

@Component
public class CreatedState implements OrderState {
    @Override
    public Order created() {
        return null;
    }

    @Override
    public Order pending() {
        return null;
    }

    @Override
    public Order cancelled() {
        return null;
    }

    @Override
    public Order failed() {
        return null;
    }

    @Override
    public Order completed() {
        return null;
    }

    @Override
    public Order refunded() {
        return null;
    }
}
