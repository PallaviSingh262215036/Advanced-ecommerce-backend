package com.example.user_management.service;

import com.example.user_management.dto.ProductRequestDTO;
import com.example.user_management.dto.ProductResponseDTO;
import com.example.user_management.entity.Category;
import com.example.user_management.entity.Product;
import com.example.user_management.entity.User;
import com.example.user_management.repository.CategoryRepository;
import com.example.user_management.repository.ProductRepository;
import com.example.user_management.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          UserRepository userRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

   
    public ProductResponseDTO createProduct(ProductRequestDTO dto,
                                        Authentication authentication) {

    String email = authentication.getName();

    User seller = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Category category = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

    Product product = new Product();

    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setStock(dto.getStock());

    product.setCategory(category);
    product.setSeller(seller);

    Product savedProduct = productRepository.save(product);

    return mapToDto(savedProduct);
}
  
    private ProductResponseDTO mapToDto(Product product) {

    return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getCategory().getName(),
            product.getSeller().getEmail()
    );
}

public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {

    Page<Product> products =
            productRepository.findAll(pageable);

    return products.map(this::mapToDto);
}

public Page<ProductResponseDTO> searchProducts(
        String keyword,
        Pageable pageable) {

    Page<Product> products =
            productRepository
                    .findByNameContainingIgnoreCase(
                            keyword,
                            pageable
                    );

    return products.map(this::mapToDto);
}

public Page<ProductResponseDTO> getProductsByCategory(
        String categoryName,
        Pageable pageable) {

    Page<Product> products =
            productRepository
                    .findByCategory_NameIgnoreCase(
                            categoryName,
                            pageable
                    );

    return products.map(this::mapToDto);
}

}
