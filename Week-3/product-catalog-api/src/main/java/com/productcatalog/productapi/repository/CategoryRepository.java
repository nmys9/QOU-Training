package com.productcatalog.productapi.repository;

import com.productcatalog.productapi.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getAllCategory();
    Optional<Category> findCategoryById(Long id);
    Optional<Category> findCategoryByName(String categoryName);
    Category addCategory(Category category);
    void updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
