package com.example.user_management.service;

import com.example.user_management.dto.AdminAnalyticsDTO;
import com.example.user_management.repository.OrderRepository;
import com.example.user_management.repository.ProductRepository;
import com.example.user_management.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    public AdminService(UserRepository userRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository) {

        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public AdminAnalyticsDTO getAnalytics() {

        AdminAnalyticsDTO dto =
                new AdminAnalyticsDTO();

        dto.setTotalUsers(
                userRepository.count()
        );

        dto.setTotalProducts(
                productRepository.count()
        );

        dto.setTotalOrders(
                orderRepository.count()
        );

        dto.setTotalRevenue(
                orderRepository.getTotalRevenue()
        );

        return dto;
    }
}
