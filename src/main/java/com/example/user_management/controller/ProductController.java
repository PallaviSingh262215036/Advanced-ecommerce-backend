package com.example.user_management.controller;

import com.example.user_management.dto.ProductRequestDTO;
import com.example.user_management.dto.ProductResponseDTO;
import com.example.user_management.entity.Product;
import com.example.user_management.service.ProductService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

   
    @PostMapping
public ProductResponseDTO createProduct(
        @RequestBody ProductRequestDTO dto,
        Authentication authentication) {

    return productService.createProduct(dto, authentication);
}

@GetMapping
public Page<ProductResponseDTO> getAllProducts(
        Pageable pageable) {

    return productService.getAllProducts(pageable);
}

@GetMapping("/search")
public Page<ProductResponseDTO> searchProducts(
        @RequestParam String keyword,
        Pageable pageable) {

    return productService.searchProducts(
            keyword,
            pageable
    );
}

@GetMapping("/category/{categoryName}")
public Page<ProductResponseDTO> getProductsByCategory(
        @PathVariable String categoryName,
        Pageable pageable) {

    return productService.getProductsByCategory(
            categoryName,
            pageable
    );
}

}
