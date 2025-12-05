package com.productcatalog.productapi.controller;


import com.productcatalog.productapi.dto.ApiResponse;
import com.productcatalog.productapi.dto.ProductResponse;
import com.productcatalog.productapi.model.Category;
import com.productcatalog.productapi.model.Product;
import com.productcatalog.productapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProduct(){
        List<ProductResponse> products= productService.getAllProduct();

        ApiResponse<List<ProductResponse>> response=ApiResponse.success(
                HttpStatus.OK.value(),
                "Products retrieved successfully",
                products
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<Product>> findProductById(@RequestParam Long id){
        Product product= productService.findProductById(id);

        ApiResponse<Product> response=ApiResponse.success(
                HttpStatus.OK.value(),
                "Product retrieved successfully.",
                product
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(params = "categoryID")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByCategoryId(@RequestParam Long categoryID){
        List<Product> products= productService.getProductsByCategoryId(categoryID);

        ApiResponse<List<Product>> response=ApiResponse.success(
                HttpStatus.OK.value(),
                "Products retrieved successfully",
                products
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(params = "categoryName")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByCategoryName(@RequestParam String categoryName){
        List<Product> products= productService.getProductsByCategoryName(categoryName);

        ApiResponse<List<Product>> response=ApiResponse.success(
                HttpStatus.OK.value(),
                "Products retrieved successfully",
                products
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> addProduct(@Valid @RequestBody Product newProduct){
        Product product = productService.addProduct(newProduct);

        ApiResponse<Product> response=ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Product created successfully.",
                product
        );
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping(params = "id")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@RequestParam Long id,@Valid @RequestBody Product updateProduct){
        Product product= productService.updateProduct(id,updateProduct);

        ApiResponse<Product> response=ApiResponse.success(
                HttpStatus.OK.value(),
                "Product updated successfully.",
                product
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void>  deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
