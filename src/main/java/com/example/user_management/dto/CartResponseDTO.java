package com.example.user_management.dto;

import java.util.List;

public class CartResponseDTO {

    private List<CartItemResponseDTO> items;

    private Double totalAmount;

    public CartResponseDTO() {
    }

    public CartResponseDTO(List<CartItemResponseDTO> items,
                           Double totalAmount) {

        this.items = items;
        this.totalAmount = totalAmount;
    }

    public List<CartItemResponseDTO> getItems() {
        return items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}
