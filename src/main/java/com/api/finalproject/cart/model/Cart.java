package com.api.finalproject.cart.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("cart")
public class Cart {

    private String email;
    private List<CartItem> cartItemsList;

}
