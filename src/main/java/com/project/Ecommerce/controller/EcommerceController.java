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
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Ecommerce")
public class EcommerceController {
    UserService userService;
    @Autowired
    public EcommerceController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin(origins = "*")

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> postUser(@RequestBody User user){
        try {
            userService.createUser(user);
            Map<String, String> response = new HashMap<>();
            response.put("message", "You have registered successfully!");
            return ResponseEntity.ok(response);
        }catch(InvalidCredential e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }catch(MessagingException e) {
            throw new RuntimeException(e);
        }catch(UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }


    @CrossOrigin(origins = "*")

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try {
            User userLogin = userService.login(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(userLogin);
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @CrossOrigin(origins = "*")

    @PostMapping("/CheckUserCreation")
    public ResponseEntity<?> checkUserCreation(@RequestBody User user) {
        try {
            User userCheck = userService.checkCreatedUser(user);
            return ResponseEntity.ok(userCheck + "User is not here!");
        } catch (UserServiceException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is here!");
        }
    }


    @CrossOrigin(origins = "*")

    @GetMapping("/user/{userId}")
    public Optional<User> getUserById(@PathVariable long userId){
        return userService.getUserById(userId);

    }

    @CrossOrigin(origins = "*")

    // Inside EcommerceController


    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutUser(@RequestBody User user) {
        try {
            userService.checkoutUser(user);
            return ResponseEntity.ok(Map.of("message", "Checkout successful!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/currentUser")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        if (principal == null) {
            // Return an appropriate error response if there's no logged-in user
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        User user = userService.findByEmail(principal.getName());
        if (user == null) {
            // Return an appropriate error response if the user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }






}
