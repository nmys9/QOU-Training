package com.productcatalog.productapi.controller;

import com.productcatalog.productapi.dto.ApiResponse;
import com.productcatalog.productapi.model.Category;
import com.productcatalog.productapi.repository.CategoryRepository;
import com.productcatalog.productapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>>  getAllCategory(){
        List<Category> categories=categoryService.getAllCategory();

        ApiResponse<List<Category>> response=ApiResponse.success(
                HttpStatus.OK.value(),
                "Categories retrieved successfully",
                categories
                );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<Category>> findCategoryById(@RequestParam long id) {
        Category category= categoryService.findCategoryById(id);

        ApiResponse<Category> response=ApiResponse.success(
                HttpStatus.OK.value(),
                "Category retrieved successfully.",
                category
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(params = "categoryName")
    public ResponseEntity<ApiResponse<Category>> findGategoryByName(@RequestParam String categoryName){
        Category category= categoryService.findCategoryByName(categoryName);

        ApiResponse<Category> response=ApiResponse.success(
                HttpStatus.OK.value(),
                "Category retrieved successfully.",
                category
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> addCategory(@Valid @RequestBody  Category newCategory){
        Category category= categoryService.addCategory(newCategory);

        ApiResponse<Category> response=ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Category created successfully.",
                category
        );

        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    @PutMapping(params = "id")
    public ResponseEntity<ApiResponse<Category>> updateCategory(@RequestParam long id, @Valid @RequestBody Category updateCategory){
        Category category= categoryService.updateCategory(id,updateCategory);
        ApiResponse<Category> response=ApiResponse.success(
                HttpStatus.OK.value(),
                "Category updated successfully.",
                category
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping( params = "id")
    public ResponseEntity<Void> deleteCategory(@RequestParam long id){
        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
