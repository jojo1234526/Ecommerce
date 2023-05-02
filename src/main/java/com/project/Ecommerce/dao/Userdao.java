package com.project.Ecommerce.dao;

import com.project.Ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Userdao extends JpaRepository<User, Long> {
    List<User> findByUserId(Long userId);

    List<User> findAllById(Long id);


    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);


}
