package com.nari.shopsphere_backend.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nari.shopsphere_backend.entity.Product;
import com.nari.shopsphere_backend.exception.ProductNotFoundException;
import com.nari.shopsphere_backend.repository.ProductRepository;
import com.nari.shopsphere_backend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    
    // Add Product
    @Override
    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    
    // Get All Products
    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    
    // Get Product By ID
    @Override
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with ID : " + id));
    }

    
    // Update Product
    @Override
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {

            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            existingProduct.setImageUrl(product.getImageUrl());
            existingProduct.setCategory(product.getCategory());

            return productRepository.save(existingProduct);
        }

        return null;
    }

    
    // Delete Product
    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

}