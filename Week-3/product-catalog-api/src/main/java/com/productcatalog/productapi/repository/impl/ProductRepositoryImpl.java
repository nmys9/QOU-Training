package com.productcatalog.productapi.repository.impl;

import com.productcatalog.productapi.model.Product;
import com.productcatalog.productapi.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    List<Product> products=new ArrayList<>();

    @Override
    public List<Product> getAllProduct() {
        return products;
    }

    @Override
    public Optional<Product> findProductByID(long id) {
        return products.stream()
                .filter(p -> p.id() == id)
                .findFirst();
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        return products.stream()
                .filter(p -> p.category().name().equalsIgnoreCase(categoryName))
                .toList();
    }

    @Override
    public List<Product> getProductsByCategoryId(long id) {
        return products.stream()
                .filter(p -> p.category().id() == id)
                .toList();
    }

    @Override
    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public void updateProduct(long id, Product product) {
        int index=-1;
        for(int i=0;i<products.size();i++){
            if(products.get(i).id() == id){
                index=i;
                break;
            }
        }

        if(index!=-1){
            products.set(index,product);
        }
    }

    @Override
    public void deleteProduct(long id) {
        products.removeIf(p -> p.id() == id);
    }
}
