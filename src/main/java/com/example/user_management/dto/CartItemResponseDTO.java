package com.example.user_management.dto;

public class CartItemResponseDTO {

    private String productName;

    private Integer quantity;

    private Double price;

    private Double subtotal;

    public CartItemResponseDTO() {
    }

    public CartItemResponseDTO(String productName,
                               Integer quantity,
                               Double price,
                               Double subtotal) {

        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSubtotal() {
        return subtotal;
    }
}
