package com.api.finalproject.orders.controller;

import com.api.finalproject.orders.model.Order;
import com.api.finalproject.orders.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public Order generateOrder() {
        return orderService.generate();
    }
}
