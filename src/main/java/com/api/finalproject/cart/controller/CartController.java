package com.api.finalproject.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.finalproject.cart.model.Cart;

import com.api.finalproject.cart.model.CartItemRequest;
import com.api.finalproject.cart.service.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public Cart addItemToCart(@RequestBody CartItemRequest cartItemRequest) {
        return cartService.addItemToCart(cartItemRequest);
    }

    @GetMapping
    public Cart getProducts() {
        return cartService.getCartProducts();
    }

}
