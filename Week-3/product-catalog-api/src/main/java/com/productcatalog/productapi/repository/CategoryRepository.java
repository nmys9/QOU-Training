package com.productcatalog.productapi.repository;

import com.productcatalog.productapi.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getAllCategory();
    Optional<Category> findCategoryById(long id);
    Optional<Category> findCategoryByName(String categoryName);
    Category addCategory(Category category);
    void updateCategory(long id, Category category);
    void deleteCategory(long id);
}
