package com.api.finalproject.cart.repository;

import java.util.ArrayList;
import java.util.List;

import com.api.finalproject.cart.model.Cart;
import com.api.finalproject.cart.model.CartItem;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CartRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public CartItem addItem(CartItem item) {
        return mongoTemplate.save(item);
    }

    public Cart findCartByUserEmail(String userEmail) {
        Query query = new Query().addCriteria(Criteria.where("email").is(userEmail));
        return mongoTemplate.findOne(query, Cart.class);
    }

    public Cart createCart(String email, CartItem item) {
        List<CartItem> items = new ArrayList<>();
        items.add(item);
        return mongoTemplate.save(new Cart(email, items));
    }

    public Cart updateCart(String userEmail, CartItem cartItem) {
        Cart cart = this.findCartByUserEmail(userEmail);

        List<CartItem> newItems = this.generateNewItemList(cart.getCartItemsList(), cartItem);
        cart.setCartItemsList(newItems);

        Query query = new Query().addCriteria(Criteria.where("email").is(userEmail));
        Update update = new Update();

        update.set("cartItemsList", newItems);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Cart.class);

        long modifiedCount = result.getModifiedCount();

        if (modifiedCount != 0) {
            log.info("Result ok");
        } else {
            log.info("Result with error");
        }

        return this.findCartByUserEmail(userEmail);
    }

    private List<CartItem> generateNewItemList(List<CartItem> itemList, CartItem cartItem) {

        int itemIndex = 0;
        boolean listHasItem = false;
        for (CartItem item : itemList) {
            if (item.getProduct().getId().equals(cartItem.getId())) {
                listHasItem = true;
            }
            itemIndex++;
        }

        CartItem cartItemToUpdate;
        if (listHasItem) {
            cartItemToUpdate = itemList.get(itemIndex - 1);
            int prevProductQuantity = cartItemToUpdate.getQuantity();
            cartItemToUpdate.setQuantity(prevProductQuantity + cartItem.getQuantity());
            itemList.set(itemIndex - 1, cartItemToUpdate);
        } else {
            cartItemToUpdate = cartItem;
            itemList.add(cartItemToUpdate);
        }

        return itemList;
    }

    // public CartItem findByProductIdAndUserEmail(String productId, String
    // userEmail) {
    // Query query = new
    // Query().addCriteria(Criteria.where("productId").is(productId).and("userEmail").is(userEmail));

    // return mongoTemplate.find(query, CartItem.class).size() != 0 ?
    // mongoTemplate.find(query, CartItem.class).get(0)
    // : null;
    // }

    // public boolean cleanCart(String userEmail) {
    // Query query = new
    // Query().addCriteria(Criteria.where("userEmail").is(userEmail));
    // try {
    // mongoTemplate.findAllAndRemove(query, CartItem.class);
    // return true;
    // } catch (Exception e) {
    // log.error("An error has ocurred: {}", e.getMessage());
    // return false;
    // }

    // }

}