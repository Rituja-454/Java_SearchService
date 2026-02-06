package com.inventory.config;

import com.inventory.model.Product;
import com.inventory.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.List;


@Configuration
public class DataInitializerConfig {

    
    @Bean
    public CommandLineRunner initializeData(ProductService productService) {
        return args -> {
            // Create 5 sample products
            List<Product> products = Arrays.asList(
                new Product(null, "Laptop", 899.99),
                new Product(null, "Mouse", 25.50),
                new Product(null, "Keyboard", 75.00),
                new Product(null, "Monitor", 299.99),
                new Product(null, "USB Cable", 12.99)
            );
            
            
            try {
                List<Product> existing = productService.getAllProducts();
                if (existing == null || existing.isEmpty()) {
                    productService.saveAllProducts(products);
                    System.out.println("✓ Sample products initialized successfully!");
                } else {
                    System.out.println("→ Sample products already exist. Skipping initialization.");
                }
            } catch (Exception e) {
                System.err.println("! Skipping data initialization due to DB access error: " + e.getMessage());
            }
        };
    }
}
