package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    // Dependency injection through constructor (better than @Autowired field injection)
    private final ProductRepository productRepository;

    // Constructor - Spring automatically injects ProductRepository here
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products from database
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Save a single product to database
    public Product saveProduct(@NonNull Product product) {
        return productRepository.save(product);
    }

   
    public void saveAllProducts(@NonNull List<Product> products) {
        productRepository.saveAll(products);
    }

    // Remove duplicate products (keeps the first occurrence for each name+price)
    public int deduplicateProducts() {
        List<Product> all = productRepository.findAll();
        Map<String, List<Product>> grouped = all.stream()
            .collect(Collectors.groupingBy(p -> p.getName() + "||" + p.getPrice()));

        List<String> idsToDelete = new ArrayList<>();
        for (List<Product> group : grouped.values()) {
            if (group.size() > 1) {
                group.stream()
                    .skip(1)
                    .map(Product::getId)
                    .filter(Objects::nonNull)
                    .forEach(idsToDelete::add);
            }
        }

        if (!idsToDelete.isEmpty()) {
            productRepository.deleteAllById(idsToDelete);
        }

        return idsToDelete.size();
    }
}
