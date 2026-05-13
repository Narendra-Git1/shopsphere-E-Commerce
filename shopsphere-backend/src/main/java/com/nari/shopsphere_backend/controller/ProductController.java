package com.nari.shopsphere_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nari.shopsphere_backend.dto.ProductDTO;
import com.nari.shopsphere_backend.entity.Category;
import com.nari.shopsphere_backend.entity.Product;
import com.nari.shopsphere_backend.payload.ApiResponse;
import com.nari.shopsphere_backend.repository.CategoryRepository;
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
    public ResponseEntity<ApiResponse<Product>>
    addProduct(
            @Valid @RequestBody ProductDTO productDTO) {

        Category category = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category Not Found"));

        
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setImageUrl(productDTO.getImageUrl());

        
        // SET CATEGORY
        product.setCategory(category);

        
        Product savedProduct =
                productService.addProduct(product);

        
        ApiResponse<Product> response =
                new ApiResponse<>(
                        true,
                        "Product Added Successfully",
                        savedProduct);

        
        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED);
    }

    
    // GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>>
    getAllProducts() {

        List<Product> products =
                productService.getAllProducts();

        
        ApiResponse<List<Product>> response =
                new ApiResponse<>(
                        true,
                        "Products Fetched Successfully",
                        products);

        
        return ResponseEntity.ok(response);
    }

    
    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>>
    getProductById(
            @PathVariable Long id) {

        Product product =
                productService.getProductById(id);

        
        ApiResponse<Product> response =
                new ApiResponse<>(
                        true,
                        "Product Fetched Successfully",
                        product);

        
        return ResponseEntity.ok(response);
    }

    
    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>>
    updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {

        Category category = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category Not Found"));

        
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setImageUrl(productDTO.getImageUrl());

        
        // SET CATEGORY
        product.setCategory(category);

        
        Product updatedProduct =
                productService.updateProduct(id, product);

        
        ApiResponse<Product> response =
                new ApiResponse<>(
                        true,
                        "Product Updated Successfully",
                        updatedProduct);

        
        return ResponseEntity.ok(response);
    }

    
    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>>
    deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        
        ApiResponse<String> response =
                new ApiResponse<>(
                        true,
                        "Product Deleted Successfully",
                        null);

        
        return ResponseEntity.ok(response);
    }

    
    // PAGINATION API
    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<Page<Product>>>
    getProductsWithPagination(

            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        Page<Product> products =
                productService.getProductsWithPagination(
                        page,
                        size,
                        sortBy);

        
        ApiResponse<Page<Product>> response =
                new ApiResponse<>(
                        true,
                        "Products Fetched Successfully",
                        products);

        
        return ResponseEntity.ok(response);
    }

    
    // SEARCH API
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<Product>>>
    searchProducts(

            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size) {

        Page<Product> products =
                productService.searchProducts(
                        keyword,
                        page,
                        size);

        
        ApiResponse<Page<Product>> response =
                new ApiResponse<>(
                        true,
                        "Products Searched Successfully",
                        products);

        
        return ResponseEntity.ok(response);
    }
    
 // FILTER BY CATEGORY
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<Product>>>
    getProductsByCategory(
            @PathVariable Long categoryId) {

        List<Product> products =
                productService.getProductsByCategory(
                        categoryId);

        
        ApiResponse<List<Product>> response =
                new ApiResponse<>(
                        true,
                        "Products Fetched Successfully",
                        products);

        
        return ResponseEntity.ok(response);
    }


    // FILTER BY PRICE RANGE
    @GetMapping("/price")
    public ResponseEntity<ApiResponse<List<Product>>>
    getProductsByPriceRange(

            @RequestParam Double min,
            @RequestParam Double max) {

        List<Product> products =
                productService.getProductsByPriceRange(
                        min,
                        max);

        
        ApiResponse<List<Product>> response =
                new ApiResponse<>(
                        true,
                        "Products Fetched Successfully",
                        products);

        
        return ResponseEntity.ok(response);
    }


    // SORT PRICE LOW TO HIGH
    @GetMapping("/sort/asc")
    public ResponseEntity<ApiResponse<List<Product>>>
    sortLowToHigh() {

        List<Product> products =
                productService
                        .getProductsPriceLowToHigh();

        
        ApiResponse<List<Product>> response =
                new ApiResponse<>(
                        true,
                        "Products Sorted Low To High",
                        products);

        
        return ResponseEntity.ok(response);
    }


    // SORT PRICE HIGH TO LOW
    @GetMapping("/sort/desc")
    public ResponseEntity<ApiResponse<List<Product>>>
    sortHighToLow() {

        List<Product> products =
                productService
                        .getProductsPriceHighToLow();

        
        ApiResponse<List<Product>> response =
                new ApiResponse<>(
                        true,
                        "Products Sorted High To Low",
                        products);

        
        return ResponseEntity.ok(response);
    }

}