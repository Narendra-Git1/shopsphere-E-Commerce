package com.nari.shopsphere_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nari.shopsphere_backend.entity.Category;
import com.nari.shopsphere_backend.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    
    // ADD CATEGORY
    @PostMapping
    public ResponseEntity<Category> addCategory(
            @RequestBody Category category) {

        Category savedCategory =
                categoryService.addCategory(category);

        return new ResponseEntity<>(
                savedCategory,
                HttpStatus.CREATED);
    }

    
    // GET ALL CATEGORIES
    @GetMapping
    public ResponseEntity<List<Category>>
    getAllCategories() {

        return ResponseEntity.ok(
                categoryService.getAllCategories());
    }

    
    // GET CATEGORY BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Category>
    getCategoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoryService.getCategoryById(id));
    }

    
    // UPDATE CATEGORY
    @PutMapping("/{id}")
    public ResponseEntity<Category>
    updateCategory(
            @PathVariable Long id,
            @RequestBody Category category) {

        return ResponseEntity.ok(
                categoryService.updateCategory(
                        id,
                        category));
    }

    
    // DELETE CATEGORY
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.ok(
                "Category Deleted Successfully");
    }

}