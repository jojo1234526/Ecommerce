package com.project.Ecommerce.controller;

import com.project.Ecommerce.exceptions.InvalidCredential;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.mail.MessagingException;


import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin("*")
@RequestMapping("/Ecommerce")
public class EcommerceController {
    UserService userService;
    @Autowired
    public EcommerceController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> postUser(@RequestBody User user){
        try {
            userService.createUser(user);
            return ResponseEntity.ok("You have registered successfully!");
        }catch(InvalidCredential e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(MessagingException e) {
            throw new RuntimeException(e);
        }catch(UnsupportedEncodingException e){
            throw new RuntimeException(e);

        }
    }

}
