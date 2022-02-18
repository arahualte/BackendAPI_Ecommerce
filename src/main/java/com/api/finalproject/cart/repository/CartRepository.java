package com.api.finalproject.cart.repository;

import java.util.List;

import com.api.finalproject.cart.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CartRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public CartItem addItem(CartItem item) {
        return mongoTemplate.save(item);
    }

    public List<CartItem> findByUserEmail(String userEmail) {
        Query query = new Query().addCriteria(Criteria.where("userEmail").is(userEmail));

        return mongoTemplate.find(query, CartItem.class);
    }

    public boolean cleanCart(String userEmail) {
        Query query = new Query().addCriteria(Criteria.where("userEmail").is(userEmail));
        try {
            mongoTemplate.findAllAndRemove(query, CartItem.class);
            return true;
        } catch (Exception e) {
            log.error("An error has ocurred: {}", e.getMessage());
            return false;
        }

    }

}