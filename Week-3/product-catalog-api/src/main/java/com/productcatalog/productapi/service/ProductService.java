package com.productcatalog.productapi.service;

import com.productcatalog.productapi.dto.ProductResponse;
import com.productcatalog.productapi.model.Product;

import java.util.*;

public interface ProductService {
    List<ProductResponse> getAllProduct();
    Product findProductById(long id);
    List<Product> getProductsByCategoryName(String categoryName);
    List<Product> getProductsByCategoryId(long id);
    Product addProduct(Product product);
    Product updateProduct(long id, Product product);
    void deleteProduct(long id);
}
