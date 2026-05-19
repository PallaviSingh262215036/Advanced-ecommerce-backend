package com.example.user_management.repository;

import com.example.user_management.entity.Cart;
import com.example.user_management.entity.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository
        extends JpaRepository<Cart, Long> {

    
    @EntityGraph(attributePaths = {
        "cartItems",
        "cartItems.product"
    })
Optional<Cart> findByUser(User user);


}
