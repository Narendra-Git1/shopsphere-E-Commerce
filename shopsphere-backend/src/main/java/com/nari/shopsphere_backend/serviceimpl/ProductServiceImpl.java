package com.nari.shopsphere_backend.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nari.shopsphere_backend.entity.Product;
import com.nari.shopsphere_backend.exception.ProductNotFoundException;
import com.nari.shopsphere_backend.repository.ProductRepository;
import com.nari.shopsphere_backend.service.ProductService;

@Service
public class ProductServiceImpl
        implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    
    // ADD PRODUCT
    @Override
    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    
    // GET ALL ACTIVE PRODUCTS
    @Override
    public List<Product> getAllProducts() {

        return productRepository.findByActiveTrue();
    }

    
    // GET PRODUCT BY ID
    @Override
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not Found With ID : " + id));
    }

    
    // UPDATE PRODUCT
    @Override
    public Product updateProduct(
            Long id,
            Product product) {

        Product existingProduct =
                productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not Found"));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }

    
    // SOFT DELETE PRODUCT
    @Override
    public void deleteProduct(Long id) {

        Product product =
                productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product Not Found"));

        product.setActive(false);

        productRepository.save(product);
    }

    
    // PAGINATION
    @Override
    public Page<Product> getProductsWithPagination(
            int page,
            int size,
            String sortBy) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy));

        return productRepository.findAll(pageable);
    }

    
    // SEARCH PRODUCTS
    @Override
    public Page<Product> searchProducts(
            String keyword,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return productRepository
                .findByNameContainingIgnoreCase(
                        keyword,
                        pageable);
    }

    
    // FILTER BY CATEGORY
    @Override
    public List<Product> getProductsByCategory(
            Long categoryId) {

        return productRepository
                .findByCategoryId(categoryId);
    }

    
    // FILTER BY PRICE RANGE
    @Override
    public List<Product> getProductsByPriceRange(
            Double min,
            Double max) {

        return productRepository
                .findByPriceBetween(min, max);
    }

    
    // SORT PRICE LOW TO HIGH
    @Override
    public List<Product> getProductsPriceLowToHigh() {

        return productRepository
                .findAll(
                        Sort.by(
                                Sort.Direction.ASC,
                                "price"));
    }

    
    // SORT PRICE HIGH TO LOW
    @Override
    public List<Product> getProductsPriceHighToLow() {

        return productRepository
                .findAll(
                        Sort.by(
                                Sort.Direction.DESC,
                                "price"));
    }
}