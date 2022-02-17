package com.api.finalproject.model;

import lombok.Data;

@Data
public class CartItem {
    private Product product;
    private int quantity;
}
