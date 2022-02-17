package com.api.finalproject.cart.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("cart")
public class Cart {
    private String username;
    private String email;
    List<CartItem> cartItemsList;
    private float totalAmountToPay;

    public void addItemtoCart(CartItem item) {
        this.cartItemsList.add(item);
    }

    public Float calculateTotalToPay(List<CartItem> cartItemsList) {
        float total = 0;
        float calc = 0;
        for (int i = 0; i < cartItemsList.size(); i++) {
            calc = cartItemsList.get(i).getProduct().getUnitPrice() * cartItemsList.get(i).getQuantity();
            total = total + calc;
        }
        return total;
    }

}
