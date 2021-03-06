package com.api.finalproject.orders.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.api.finalproject.cart.model.CartItem;
import com.api.finalproject.users.model.User;

import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
@Document("orders")
public class Order {

    @Id
    private String orderId;
    private User buyer;
    List<CartItem> itemsBought;
    private float totalPaid;

}
