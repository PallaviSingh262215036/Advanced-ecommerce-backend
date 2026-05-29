package com.example.user_management.controller;

import com.example.user_management.dto.OrderResponseDTO;
import com.example.user_management.service.OrderService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public String placeOrder(
            Authentication authentication) {

        return orderService.placeOrder(authentication);
    }

    @GetMapping("/my-orders")
public List<OrderResponseDTO> getMyOrders(
        Authentication authentication) {

    return orderService.getMyOrders(authentication);
}
}
