package com.api.finalproject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("products")
public class Product {
    @Id
    private String id;
    private String name;
    private String category;
    private String description;
    private Float unitPrice;
    // private int stock;
}
