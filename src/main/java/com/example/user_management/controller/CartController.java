package com.example.user_management.controller;

import com.example.user_management.dto.AddToCartRequestDTO;
import com.example.user_management.dto.CartResponseDTO;
import com.example.user_management.service.CartService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public String addToCart(
            @RequestBody AddToCartRequestDTO dto,
            Authentication authentication) {

        cartService.addToCart(dto, authentication);

        return "Product added to cart";
    }

    @GetMapping
public CartResponseDTO getCart(
        Authentication authentication) {

    return cartService.getCart(authentication);
}
}
