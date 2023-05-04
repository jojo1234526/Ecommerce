package com.project.Ecommerce.controller;

import com.project.Ecommerce.exceptions.InvalidCredential;
import com.project.Ecommerce.exceptions.UserNotFoundException;
import com.project.Ecommerce.exceptions.UserServiceException;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.mail.MessagingException;


import java.io.UnsupportedEncodingException;
import java.util.Optional;

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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try {
            User userLogin = userService.login(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(userLogin);
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/CheckUserCreation")
    public ResponseEntity<?> checkUserCreation(@RequestBody User user) {
        try {
            User userCheck = userService.checkCreatedUser(user);
            return ResponseEntity.ok(userCheck + "User is not here!");
        } catch (UserServiceException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is here!");
        }
    }


    @GetMapping("/user/{userId}")
    public Optional<User> getUserById(@PathVariable long userId){
        return userService.getUserById(userId);

    }

}
