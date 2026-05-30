package com.example.user_management.service;


import com.example.user_management.dto.CategoryRequestDTO;
import com.example.user_management.dto.CategoryResponseDTO;
import com.example.user_management.entity.Category;
import com.example.user_management.repository.CategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {

        Category category = new Category();
        category.setName(dto.getName());

        Category saved = categoryRepository.save(category);

        return new CategoryResponseDTO(
                saved.getId(),
                saved.getName()
        );
    }
}