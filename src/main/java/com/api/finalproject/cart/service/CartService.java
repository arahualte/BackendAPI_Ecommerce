package com.api.finalproject.cart.service;

import com.api.finalproject.cart.model.Cart;
import com.api.finalproject.cart.model.CartItem;
import com.api.finalproject.cart.model.CartItemRequest;
import com.api.finalproject.cart.repository.CartRepository;
import com.api.finalproject.products.model.Product;
import com.api.finalproject.products.repository.ProductRepository;
import com.api.finalproject.users.repository.UserRepository;
import com.api.finalproject.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    public Cart addItemToCart(CartItemRequest cartItemRequest) {
        Product product = productsRepository.findProductById(cartItemRequest.getProductId());
        CartItem cartItem = new CartItem();
        cartItem.setId(product.getId());
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemRequest.getQuantity());
        String userEmail = jwtUtil.getEmailFromToken();
        Cart existentCart = cartRepository.findCartByUserEmail(userEmail);
        Cart resultCart;

        if (existentCart == null) {
            resultCart = cartRepository.createCart(userEmail, cartItem);
        } else {
            resultCart = cartRepository.updateCart(userEmail, cartItem);

        }

        return resultCart;
    }

    public Cart getCartProducts() {
        String userEmail = jwtUtil.getEmailFromToken();
        return cartRepository.findCartByUserEmail(userEmail);
    }

}
