package com.nari.shopsphere_backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.shopsphere_backend.entity.Product;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    
    // SEARCH PRODUCTS
    Page<Product> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable);

    
    // FILTER BY CATEGORY
    List<Product> findByCategoryId(
            Long categoryId);

    
    // FILTER BY PRICE RANGE
    List<Product> findByPriceBetween(
            Double min,
            Double max);

    
    // SORT LOW TO HIGH
    List<Product> findByOrderByPriceAsc();

    
    // SORT HIGH TO LOW
    List<Product> findByOrderByPriceDesc();

}