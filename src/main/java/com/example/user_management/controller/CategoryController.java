package com.example.user_management.controller;

import com.example.user_management.dto.CategoryRequestDTO;
import com.example.user_management.dto.CategoryResponseDTO;
import com.example.user_management.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CategoryResponseDTO createCategory(
            @Valid @RequestBody CategoryRequestDTO dto) {

        return categoryService.createCategory(dto);
    }
}
