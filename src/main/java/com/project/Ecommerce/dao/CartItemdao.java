package com.project.Ecommerce.dao;

import com.project.Ecommerce.model.CartItem;
import com.project.Ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemdao extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);

}
