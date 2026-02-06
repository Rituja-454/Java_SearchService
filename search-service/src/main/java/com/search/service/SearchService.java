package com.search.service;

import com.search.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchService {

    private final RestTemplate restTemplate;

    // URL of the Inventory Service
    private static final String INVENTORY_SERVICE_URL = "http://localhost:8081/products";

    // Constructor injection
    public SearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to search for products under $50 and return only their names
    public List<String> searchProductsUnder50() {
        try {
            // Step 1: Call Inventory Service to get all products
            Product[] productsArray = restTemplate.getForObject(
                INVENTORY_SERVICE_URL,
                Product[].class
            );

            // Step 2: Convert array to list for easier stream operations
            List<Product> products = Arrays.asList(productsArray);

            // Step 3: Use Java Stream API to:
            // - Filter products with price < 50
            // - Map to product names only
            // - Sort alphabetically
            // - Collect as list
            List<String> filteredProducts = products.stream()
                .filter(product -> product.getPrice() < 50)  // Filter by price
                .map(product -> product.getName())            // Get only names
                .sorted()                                      // Sort alphabetically
                .toList();                                     // Collect as list

            return filteredProducts;

        } catch (Exception e) {
            System.err.println("Error calling Inventory Service: " + e.getMessage());
            return List.of();  // Return empty list on error
        }
    }
}
