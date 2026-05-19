package com.example.user_management.repository;

import com.example.user_management.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {
            "category",
            "seller"
    })
    Page<Product> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {
        "category",
        "seller"
   })
   Page<Product> findByNameContainingIgnoreCase(
        String keyword,
        Pageable pageable
);

@EntityGraph(attributePaths = {
        "category",
        "seller"
})
Page<Product> findByCategory_NameIgnoreCase(
        String categoryName,
        Pageable pageable
);

}
