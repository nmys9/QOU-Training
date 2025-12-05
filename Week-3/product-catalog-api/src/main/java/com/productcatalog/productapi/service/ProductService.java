package com.productcatalog.productapi.service;

import com.productcatalog.productapi.dto.ProductResponse;
import com.productcatalog.productapi.model.Product;

import java.util.*;

public interface ProductService {
    List<ProductResponse> getAllProduct();
    Product findProductById(Long id);
    List<Product> getProductsByCategoryName(String categoryName);
    List<Product> getProductsByCategoryId(Long id);
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
