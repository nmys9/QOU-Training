package com.productcatalog.productapi.service.impl;

import com.productcatalog.productapi.exception.ResourceConflictException;
import com.productcatalog.productapi.model.Category;
import com.productcatalog.productapi.model.Product;
import com.productcatalog.productapi.repository.CategoryRepository;
import com.productcatalog.productapi.repository.ProductRepository;
import com.productcatalog.productapi.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,ProductRepository productRepository){
        this.categoryRepository=categoryRepository;
        this.productRepository=productRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.getAllCategory();
    }

    @Override
    public Category findCategoryById(long id) {
        return categoryRepository.findCategoryById(id).orElseThrow(
                () -> new NoSuchElementException("Category with ID :" + id + " cannot found")
        );
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByName(categoryName).orElseThrow(
                () -> new NoSuchElementException("Category with name " + categoryName + " cannot be found")
        );
    }

    @Override
    public Category addCategory(Category category) throws ResourceConflictException {
        Optional<Category> existsCategory1= categoryRepository.findCategoryById(category.id());
        if(existsCategory1.isPresent()){
            throw new ResourceConflictException("Category","ID", category.id());
        }
        Optional<Category> existsCategory2= categoryRepository.findCategoryByName(category.name());
        if(existsCategory2.isPresent()){
            throw new ResourceConflictException("Category","Name", category.name());
        }

        return categoryRepository.addCategory(category);
    }

    @Override
    public Category updateCategory(long id, Category updateCategory) {
        findCategoryById(id);
        if(updateCategory.id() != id) {
            throw new IllegalStateException("ID in path (" + id + ") must match ID in request body (" + updateCategory.id() + ").");
        }
        categoryRepository.updateCategory(id, updateCategory);
        return updateCategory;
    }

    @Override
    public void deleteCategory(long id) {
        findCategoryById(id);
        List<Product> products=productRepository.getProductsByCategoryId(id);
        if(!products.isEmpty()){
            throw new IllegalStateException("Cannot delete category with ID " + id + " because " + products.size() + " products are still linked to it.");
        }
        categoryRepository.deleteCategory(id);
    }
}
