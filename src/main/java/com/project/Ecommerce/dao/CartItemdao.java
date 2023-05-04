package com.project.Ecommerce.dao;

import com.project.Ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemdao extends JpaRepository<CartItem, Long> {
}
