package com.productcatalog.productapi.repository.impl;

import com.productcatalog.productapi.model.Category;
import com.productcatalog.productapi.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;

@Repository("categortTest")
public class CategoryRepositoryImplTest implements CategoryRepository {
    Set<com.productcatalog.productapi.model.Category> categorys=new HashSet<>();

    @Override
    public List<com.productcatalog.productapi.model.Category> getAllCategory() {
        return categorys.stream().toList();
    }

    @Override
    public Optional<com.productcatalog.productapi.model.Category> findCategoryById(Long id) {
        return categorys.stream()
                .filter(c -> c.id() == id)
                .findFirst();
    }

    @Override
    public Optional<com.productcatalog.productapi.model.Category> findCategoryByName(String categoryName) {
        return categorys.stream()
                .filter(c -> c.name().equalsIgnoreCase(categoryName))
                .findFirst();
    }

    @Override
    public com.productcatalog.productapi.model.Category addCategory(com.productcatalog.productapi.model.Category category) {
        categorys.add(category);
        return category;
    }

    @Override
    public void updateCategory(Long id, Category updateCategory) {
        categorys.stream()
                .filter(c -> c.id() == id)
                .findFirst()
                .ifPresent(oldCategory -> {
                    categorys.remove(oldCategory);
                    categorys.add(updateCategory);
                });
    }

    @Override
    public void deleteCategory(Long id) {
        categorys.removeIf(c -> c.id() ==id);
    }
}
