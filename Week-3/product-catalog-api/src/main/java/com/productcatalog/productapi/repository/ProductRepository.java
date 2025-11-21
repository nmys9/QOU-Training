package com.productcatalog.productapi.repository;

import com.productcatalog.productapi.model.Category;
import com.productcatalog.productapi.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAllProduct();
    Optional<Product> findProductByID(long id);
    List<Product> getProductsByCategoryName(String categoryName);
    List<Product> getProductsByCategoryId(long id);
    Product addProduct(Product product);
    void updateProduct(long id, Product product);
    void deleteProduct(long id);

}
