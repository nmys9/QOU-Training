package com.productcatalog.productapi.service.impl;

import com.productcatalog.productapi.dto.ProductResponse;
import com.productcatalog.productapi.exception.ResourceConflictException;
import com.productcatalog.productapi.model.*;
import com.productcatalog.productapi.repository.ProductRepository;
import com.productcatalog.productapi.service.CategoryService;
import com.productcatalog.productapi.service.ProductService;
import org.springframework.boot.context.properties.bind.handler.NoUnboundElementsBindHandler;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public ProductServiceImpl(CategoryService categoryService,ProductRepository productRepository){
        this.categoryService=categoryService;
        this.productRepository=productRepository;
    }

    private ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.id(),
                product.title(),
                product.description(),
                product.price(),
                product.category().name()
        );
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> products=productRepository.getAllProduct();
        return products.stream()
                .map(this::toProductResponse)
                .toList();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findProductByID(id).orElseThrow(
                () -> new NoSuchElementException("Product with ID: " + id + " cannot found"));
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        categoryService.findCategoryByName(categoryName);
        return productRepository.getProductsByCategoryName(categoryName);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        categoryService.findCategoryById(id);
        return productRepository.getProductsByCategoryId(id);
    }

    @Override
    public Product addProduct(Product product) throws ResourceConflictException {
        Optional<Product> existsProduct= productRepository.findProductByID(product.id());
        if(existsProduct.isPresent()){
            throw new ResourceConflictException("Product","ID",product.id());
        }
        categoryService.findCategoryById(product.category().id());
        return productRepository.addProduct(product);
    }

    @Override
    public Product updateProduct(Long id,Product updateProduct) {
        findProductById(id);
        categoryService.findCategoryById(updateProduct.category().id());
        if(updateProduct.id() != id) {
            throw new IllegalArgumentException("ID in path (" + id + ") must match ID in request body (" + updateProduct.id() + ").");
        }
        productRepository.updateProduct(id, updateProduct);
        return updateProduct;
    }


    @Override
    public void deleteProduct(Long id) {
        findProductById(id);
        productRepository.deleteProduct(id);
    }
}
