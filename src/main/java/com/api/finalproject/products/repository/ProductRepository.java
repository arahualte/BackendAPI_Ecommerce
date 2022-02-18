package com.api.finalproject.products.repository;

import java.util.List;

import com.api.finalproject.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Product createProduct(Product product) {
        return mongoTemplate.save(product);
    }

    public Product findProductById(String id) {
        return mongoTemplate.findById(id, Product.class);
    }

    public List<Product> findAllProducts() {
        return mongoTemplate.findAll(Product.class);
    }

    public String deleteProductById(String id) {
        Query removeQuery = new Query().addCriteria(Criteria.where("id").is(id));
        mongoTemplate.findAndRemove(removeQuery, Product.class);
        return "Product has beeen deleted successfully";
    }

}