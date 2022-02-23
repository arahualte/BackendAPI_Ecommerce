package com.api.finalproject.cart.model;

import com.api.finalproject.products.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {

    private String id;
    private Product product;
    private int quantity;
}
