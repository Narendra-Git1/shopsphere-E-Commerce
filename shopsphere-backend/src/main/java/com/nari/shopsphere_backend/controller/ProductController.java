package com.nari.shopsphere_backend.controller;

import org.springframework.data.domain.Page;

import com.nari.shopsphere_backend.entity.Category;
import com.nari.shopsphere_backend.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nari.shopsphere_backend.dto.ProductDTO;
import com.nari.shopsphere_backend.entity.Product;
import com.nari.shopsphere_backend.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;

    
    // ADD PRODUCT
    @PostMapping
    public ResponseEntity<Product> addProduct(
            @Valid @RequestBody ProductDTO productDTO) {

        Category category = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category Not Found"));

        
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setImageUrl(productDTO.getImageUrl());

        
        // SET CATEGORY OBJECT
        product.setCategory(category);

        
        Product savedProduct =
                productService.addProduct(product);

        return new ResponseEntity<>(
                savedProduct,
                HttpStatus.CREATED);
    }
    
    // GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    
    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(productService.getProductById(id));
    }

    
    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setImageUrl(productDTO.getImageUrl());
        productDTO.getCategoryId();

        Product updatedProduct =
                productService.updateProduct(id, product);

        return ResponseEntity.ok(updatedProduct);
    }

    
    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                "Product Deleted Successfully");
    }
    
//    ADD PAGINATION API
    @GetMapping("/pagination")
    public ResponseEntity<Page<Product>>
    getProductsWithPagination(

            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return ResponseEntity.ok(
                productService.getProductsWithPagination(
                        page,
                        size,
                        sortBy));
    }
    
//    ADD SEARCH API
    @GetMapping("/search")
    public ResponseEntity<Page<Product>>
    searchProducts(

            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size) {

        return ResponseEntity.ok(
                productService.searchProducts(
                        keyword,
                        page,
                        size));
    }

}