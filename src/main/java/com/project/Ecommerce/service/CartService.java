package com.project.Ecommerce.service;


import com.project.Ecommerce.dao.CartItemdao;
import com.project.Ecommerce.dao.Userdao;
import com.project.Ecommerce.model.CartItem;
import com.project.Ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemdao cartItemdao;

    @Autowired
    private Userdao userdao;

    public CartItem addCartItem(Long userId, CartItem cartItem) {
        Optional<User> userOptional = userdao.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            cartItem.setUser(user);
            return cartItemdao.save(cartItem);
        } else {
            throw new RuntimeException("User not found!");
        }
    }
}
