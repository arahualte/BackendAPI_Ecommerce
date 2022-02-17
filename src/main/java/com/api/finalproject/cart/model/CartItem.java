package com.api.finalproject.cart.model;

import com.api.finalproject.products.model.Product;

import lombok.Data;

@Data
public class CartItem {
    private Product product;
    private int quantity;
}
