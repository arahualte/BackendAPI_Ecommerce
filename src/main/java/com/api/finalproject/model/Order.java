package com.api.finalproject.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.List;

import org.springframework.data.annotation.Id;

@Data
@Document("orders")
public class Order {

    @Id
    private String orderId;
    private User buyer;
    List<CartItem> itemsBought;
    private float totalPaid;

}
