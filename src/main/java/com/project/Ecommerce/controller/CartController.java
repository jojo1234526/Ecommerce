package com.project.Ecommerce.controller;
import com.project.Ecommerce.model.CartItem;
import com.project.Ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}/cart-items")
    public ResponseEntity<CartItem> addCartItem(@PathVariable("userId") Long userId, @RequestBody CartItem cartItem) {
        try {
            CartItem addedCartItem = cartService.addCartItem(userId, cartItem);
            return new ResponseEntity<>(addedCartItem, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}/cart-items")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItems(userId);
    }

}