package com.nari.shopsphere_backend.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nari.shopsphere_backend.entity.Category;
import com.nari.shopsphere_backend.entity.Product;
import com.nari.shopsphere_backend.repository.CategoryRepository;
import com.nari.shopsphere_backend.repository.ProductRepository;
import com.nari.shopsphere_backend.service.CategoryService;

@Service
public class CategoryServiceImpl
        implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    // ADD CATEGORY
    @Override
    public Category addCategory(Category category) {

        return categoryRepository.save(category);
    }


    // GET ALL CATEGORIES
    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }


    // GET CATEGORY BY ID
    @Override
    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category Not Found"));
    }


    // UPDATE CATEGORY
    @Override
    public Category updateCategory(
            Long id,
            Category category) {

        Category existingCategory =
                categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category Not Found"));

        existingCategory.setName(
                category.getName());

        return categoryRepository.save(existingCategory);
    }


    // DELETE CATEGORY
    @Override
    public void deleteCategory(Long id) {

        Category category =
                categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category Not Found"));

        List<Product> products =
                productRepository.findByCategoryId(id);

        if (!products.isEmpty()) {

            throw new RuntimeException(
                    "Cannot delete category because products exist");
        }

        categoryRepository.delete(category);
    }

}