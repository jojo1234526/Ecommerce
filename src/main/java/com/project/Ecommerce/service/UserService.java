package com.project.Ecommerce.service;

import com.project.Ecommerce.exceptions.AppConstants;
import com.project.Ecommerce.exceptions.InvalidCredential;
import com.project.Ecommerce.exceptions.UserNotFoundException;
import com.project.Ecommerce.exceptions.UserServiceException;
import com.project.Ecommerce.model.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Random;
import com.project.Ecommerce.dao.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class UserService {

    private Userdao userdao;

    @Autowired
    public UserService(Userdao userdao) {
        this.userdao = userdao;
    }


    public User createUser(User user) throws InvalidCredential, MessagingException, UnsupportedEncodingException {

        //Check if email is already exists
        User existedUser = userdao.findByEmail(user.getEmail());
        if (existedUser != null) {
            throw new InvalidCredential("Email " + user.getEmail() + " already exists");
        }
        //check if employee email and password meet the requirements
        String email = user.getEmail();
        String password = user.getPassword();

        if (!email.matches("^.+@.+$")) {
            throw new InvalidCredential("Invalid email format");
        }
//        if (password.length() < 6 || !password.matches(".*[a-zA-Z].*")) {
//            throw new InvalidCredential("Password must be at least 6 characters long and must contain letters");
//        }

        //create random number for password

        Random random = new Random();
        String randomCode = String.valueOf(random.nextInt(9999999));
        user.setPassword(randomCode);


        //Send verification email

//        emailSenderService.sendAccountRegistrationEmail(employee);


        return userdao.save(user);
    }

    public User login(String email, String password) {
        User user = userdao.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new UserNotFoundException("User with email " + email + " not found!");
        }
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    public Optional<User> getUserById(long employeeId) {

        return userdao.findById(employeeId);
    }

    public User checkCreatedUser(User user) throws UserServiceException {
        if (userdao.findByEmail(user.getEmail()) != null)
            throw new UserServiceException(AppConstants.USER_ALREADY_EXISTS);
        return userdao.save(user);
    }

    // Inside UserService
    public void checkoutUser(User user) {
        // Clear the user's cart after checkout
        user.getCartItems().clear();
        userdao.save(user);
    }




}
