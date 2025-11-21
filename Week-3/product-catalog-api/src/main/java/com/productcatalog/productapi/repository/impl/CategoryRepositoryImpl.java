package com.productcatalog.productapi.repository.impl;

import com.productcatalog.productapi.model.Category;
import com.productcatalog.productapi.repository.CategoryRepository;
import com.productcatalog.productapi.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.*;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    Set<Category> categorys=new HashSet<>();

    @Override
    public List<Category> getAllCategory() {
        return categorys.stream().toList();
    }

    @Override
    public Optional<Category> findCategoryById(long id) {
        return categorys.stream()
                .filter(c -> c.id() == id)
                .findFirst();
    }

    @Override
    public Optional<Category> findCategoryByName(String categoryName) {
        return categorys.stream()
                .filter(c -> c.name().equalsIgnoreCase(categoryName))
                .findFirst();
    }

    @Override
    public Category addCategory(Category category) {
        categorys.add(category);
        return category;
    }

    @Override
    public void updateCategory(long id, Category updateCategory) {
        categorys.stream()
                .filter(c -> c.id() == id)
                .findFirst()
                .ifPresent(oldCategory -> {
                    categorys.remove(oldCategory);
                    categorys.add(updateCategory);
                });
    }

    @Override
    public void deleteCategory(long id) {
        categorys.removeIf(c -> c.id() ==id);
    }
}
