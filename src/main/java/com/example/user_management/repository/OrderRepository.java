package com.example.user_management.repository;

import com.example.user_management.entity.OrderEntity;
import com.example.user_management.entity.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;

public interface OrderRepository
        extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUser(User user);

    @EntityGraph(attributePaths = {"orderItems"})
    List<OrderEntity> findWithItemsByUser(User user);

    @Query("""
       SELECT COALESCE(SUM(o.totalAmount), 0)
       FROM OrderEntity o
       """)
Double getTotalRevenue();
}