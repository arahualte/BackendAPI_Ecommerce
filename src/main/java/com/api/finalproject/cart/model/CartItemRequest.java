package com.api.finalproject.cart.model;

import lombok.Data;

@Data
public class CartItemRequest {

    private String productId;
    private int quantity;

}
