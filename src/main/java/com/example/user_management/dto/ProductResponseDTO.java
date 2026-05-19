package com.example.user_management.dto;

public class ProductResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private String categoryName;

    private String sellerEmail;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id,
                              String name,
                              String description,
                              Double price,
                              Integer stock,
                              String categoryName,
                              String sellerEmail) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryName = categoryName;
        this.sellerEmail = sellerEmail;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

     public String getDescription() {
        return description;
    }

     public Double getPrice() {
        return price;
    }

     public Integer getStock() {
        return stock;
    }

     public String getCategoryName() {
        return categoryName;
    }

     public String getSellerEmail() {
        return sellerEmail;
    }
}
