package com.api.finalproject.orders.repository;

import com.api.finalproject.orders.model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Order create(Order order) {
        return mongoTemplate.save(order);
    }

}
