package com.productcatalog.productapi.config;

import com.productcatalog.productapi.model.*;
import com.productcatalog.productapi.service.CategoryService;
import com.productcatalog.productapi.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ProductService productService;
    private final CategoryService categoryService;

    public DataInitializer(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Starting Data Initialization (Seeding) ---");

        // 1. تعريف وإضافة الفئات (5 Categories)
        Category electronics = new Category(101L, "Electronics");
        Category books = new Category(102L, "Books");
        Category homeGoods = new Category(103L, "Home Goods");
        Category apparel = new Category(104L, "Apparel");
        Category outdoor = new Category(105L, "Outdoor Gear");

        List<Category> categories = Arrays.asList(electronics, books, homeGoods, apparel, outdoor);

        for (Category cat : categories) {
            // يفترض أن دالة addCategory تقوم بمعالجة التكرار إذا كانت البيانات موجودة مسبقًا
            try {
                categoryService.addCategory(cat);
            } catch (Exception e) {
                System.out.println("Category already exists: " + cat.name());
            }
        }
        System.out.println("5 Categories added/verified.");

        // 2. تعريف وإضافة المنتجات (20 Products)
        List<Product> products = Arrays.asList(
                // Electronics (101) - 4 Products
                new Product(1001L, "Smartphone X9", "Latest flagship phone with 5G connectivity.", 899.99, electronics),
                new Product(1002L, "Wireless Headphones", "Noise-cancelling, 40-hour battery life.", 199.50, electronics),
                new Product(1003L, "4K Smart TV 55\"", "OLED display with advanced motion rate.", 1299.00, electronics),
                new Product(1004L, "Portable Charger 20K", "High capacity power bank for multiple charges.", 45.99, electronics),

                // Books (102) - 4 Products
                new Product(2001L, "Science Fiction Novel", "A thrilling story about time travel and paradox.", 15.00, books),
                new Product(2002L, "Cooking Basics Guide", "Step-by-step recipes for beginner cooks.", 22.75, books),
                new Product(2003L, "History of Coding", "Detailed account of programming evolution.", 35.00, books),
                new Product(2004L, "The Hidden Library", "Mystery novel set in a forgotten European city.", 18.50, books),

                // Home Goods (103) - 4 Products
                new Product(3001L, "Smart Coffee Maker", "Programmable brewer with mobile app control.", 99.00, homeGoods),
                new Product(3002L, "Bamboo Cutting Board", "Large, durable, and eco-friendly kitchen board.", 29.99, homeGoods),
                new Product(3003L, "Set of Linen Towels", "Six soft, highly absorbent linen bath towels.", 55.00, homeGoods),
                new Product(3004L, "Aromatherapy Diffuser", "Ultrasonic diffuser with LED color options.", 39.50, homeGoods),

                // Apparel (104) - 4 Products
                new Product(4001L, "Unisex Running Shoes", "Lightweight, breathable athletic footwear.", 75.99, apparel),
                new Product(4002L, "Waterproof Jacket", "Durable, windproof shell jacket.", 120.00, apparel),
                new Product(4003L, "Cotton T-Shirt 3-Pack", "High-quality essential t-shirts.", 40.00, apparel),
                new Product(4004L, "Denim Jeans (Slim Fit)", "Classic blue denim, comfortable and stylish.", 65.00, apparel),

                // Outdoor Gear (105) - 4 Products
                new Product(5001L, "Two-Person Tent", "Easy setup tent for camping trips.", 150.00, outdoor),
                new Product(5002L, "Compact First Aid Kit", "Essential medical supplies for hiking.", 25.00, outdoor),
                new Product(5003L, "Hiking Backpack 60L", "Ergonomic and large capacity for long treks.", 85.99, outdoor),
                new Product(5004L, "Insulated Water Bottle", "Keeps drinks cold for 24 hours.", 19.99, outdoor)
        );

        for (Product prod : products) {
            try {
                productService.addProduct(prod);
            } catch (Exception e) {
                System.out.println("Product already exists: " + prod.title());
            }
        }
        System.out.println("20 Products added/verified.");
        System.out.println("--- Data Initialization Complete ---");
    }
}
