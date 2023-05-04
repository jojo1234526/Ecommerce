package com.project.Ecommerce.controller;
import com.project.Ecommerce.model.CartItem;
import com.project.Ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
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
}