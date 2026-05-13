package com.nari.shopsphere_backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nari.shopsphere_backend.entity.Product;

public interface ProductService {

    
    // ADD PRODUCT
    Product addProduct(Product product);

    
    // GET ALL PRODUCTS
    List<Product> getAllProducts();

    
    // GET PRODUCT BY ID
    Product getProductById(Long id);

    
    // UPDATE PRODUCT
    Product updateProduct(Long id,
                          Product product);

    
    // DELETE PRODUCT
    void deleteProduct(Long id);

    
    // PAGINATION
    Page<Product> getProductsWithPagination(
            int page,
            int size,
            String sortBy);

    
    // SEARCH PRODUCTS
    Page<Product> searchProducts(
            String keyword,
            int page,
            int size);

    
    // FILTER BY CATEGORY
    List<Product> getProductsByCategory(
            Long categoryId);

    
    // FILTER BY PRICE RANGE
    List<Product> getProductsByPriceRange(
            Double min,
            Double max);

    
    // SORT LOW TO HIGH
    List<Product> getProductsPriceLowToHigh();

    
    // SORT HIGH TO LOW
    List<Product> getProductsPriceHighToLow();

}