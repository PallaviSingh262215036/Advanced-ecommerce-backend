package com.example.user_management.repository;

import com.example.user_management.entity.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_management.entity.Cart;
import com.example.user_management.entity.Product;

import java.util.Optional;

public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {
     
        Optional<CartItem> findByCartAndProduct(
        Cart cart,
        Product product
);
}


