package com.api.finalproject.orders.service;

import java.util.List;

import com.api.finalproject.cart.model.CartItem;
import com.api.finalproject.cart.repository.CartRepository;
import com.api.finalproject.orders.model.Order;
import com.api.finalproject.orders.repository.OrderRepository;
import com.api.finalproject.users.model.User;
import com.api.finalproject.users.repository.UserRepository;
import com.api.finalproject.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    public Order generate() {
        Order order = new Order();
        String userEmail = jwtUtil.getEmailFromToken();

        // Set order buyer
        User buyer = userRepository.findByEmail(userEmail);
        order.setBuyer(buyer);
        
        // Set list of items
        List<CartItem> itemsBought = cartRepository.findCartByUserEmail(userEmail).getCartItemsList();
        order.setItemsBought(itemsBought);

        // Calculate total to pay
        float totalPaid = this.calculateTotalAmountToPay(itemsBought);
        order.setTotalPaid(totalPaid);

        // Save order and clean the cart
        cartRepository.cleanCart(userEmail);

        log.info("Order Generation Process ended");
        return orderRepository.create(order);
    }

    public float calculateTotalAmountToPay(List<CartItem> cartItems) {
        float total = 0;

        for (int i = 0; i < cartItems.size(); i++) {
            total = total + cartItems.get(i).getProduct().getUnitPrice() *
                    cartItems.get(i).getQuantity();
        }
        return total;
    }

}
