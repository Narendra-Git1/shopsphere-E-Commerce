package com.nari.shopsphere_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.shopsphere_backend.entity.Product;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    
    // Search Products By Name
    Page<Product> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable);

}