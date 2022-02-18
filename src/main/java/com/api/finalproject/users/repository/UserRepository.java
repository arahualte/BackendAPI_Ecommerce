package com.api.finalproject.users.repository;

import com.api.finalproject.users.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public User createUser(User user) {
        return mongoTemplate.save(user);
    }

    public User findByUserName(String name) {
        Query query = new Query().addCriteria(Criteria.where("username").is(name));
        return mongoTemplate.find(query, User.class).get(0);
    };

    public User findByEmail(String email) {
        Query query = new Query().addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.find(query, User.class).get(0);
    };

}