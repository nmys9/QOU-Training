package com.productcatalog.productapi.service;

import com.productcatalog.productapi.model.Category;
import java.util.*;

public interface CategoryService {
    List<Category> getAllCategory();
    Category findCategoryById(long id);
    Category findCategoryByName(String categoryName);
    Category addCategory(Category category);
    Category updateCategory(long id, Category category);
    void deleteCategory(long id);

}
