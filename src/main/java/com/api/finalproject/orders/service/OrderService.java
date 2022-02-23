package com.api.finalproject.orders.service;

import java.util.List;

import com.api.finalproject.cart.model.CartItem;
import com.api.finalproject.cart.repository.CartRepository;
import com.api.finalproject.orders.model.Order;
import com.api.finalproject.orders.repository.OrderRepository;
import com.api.finalproject.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    JwtUtil jwtUtil;

    public Order generate() {
        Order order = new Order();
        String userEmail = jwtUtil.getEmailFromToken();

        // adding logic

        log.info("Order Generation Process ended");
        return order;
    }

    public float calculateTotalAmountToPay(List<CartItem> cartItems) {
        float total = 0;

        for (int i = 0; i < cartItems.size(); i++) {
            total = total + cartItems.get(i).getProduct().getUnitPrice() *
                    cartItems.get(i).getQuantity();
        }
        return total;
    }

}
