package com.nari.shopsphere_backend.service;

import java.util.List;

import com.nari.shopsphere_backend.entity.Product;

public interface ProductService {

    // Add Product
    Product addProduct(Product product);

    
    // Get All Products
    List<Product> getAllProducts();

    
    // Get Product By ID
    Product getProductById(Long id);

    
    // Update Product
    Product updateProduct(Long id, Product product);

    
    // Delete Product
    void deleteProduct(Long id);

}