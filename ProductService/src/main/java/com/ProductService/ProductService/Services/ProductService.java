package com.ProductService.ProductService.Services;


import com.ProductService.ProductService.Entity.Product;
import com.ProductService.ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // Create or Update a Product
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    // Get all products to show on the storefront
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get a single product by ID
    public Optional<Product> getProductById(String id) {
        return repository.findById(id);
    }

    // Crucial Business Logic for Microservices Communication
    @Transactional
    public boolean deductStock(String id, int quantity) {
        Optional<Product> productOpt = repository.findById(id);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            // Validation Check: Do we have enough inventory?
            if (product.getStockQuantity() >= quantity) {
                product.setStockQuantity(product.getStockQuantity() - quantity);
                repository.save(product);
                return true; // Stock deduction successful
            }
        }
        return false; // Product not found or Insufficient stock
    }
}
