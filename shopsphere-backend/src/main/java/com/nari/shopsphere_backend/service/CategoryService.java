package com.nari.shopsphere_backend.service;

import java.util.List;

import com.nari.shopsphere_backend.entity.Category;

public interface CategoryService {

    
    // Add Category
    Category addCategory(Category category);

    
    // Get All Categories
    List<Category> getAllCategories();

    
    // Get Category By ID
    Category getCategoryById(Long id);

    
    // Update Category
    Category updateCategory(Long id,
                            Category category);

    
    // Delete Category
    void deleteCategory(Long id);

}