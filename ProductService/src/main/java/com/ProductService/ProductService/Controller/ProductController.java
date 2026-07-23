package com.ProductService.ProductService.Controller;


import com.ProductService.ProductService.Entity.Product;
import com.ProductService.ProductService.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint to manually add products (Great for testing)
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Endpoint to get the entire catalog
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Endpoint called by Order Service via OpenFeign
    @PutMapping("/{id}/deduct")
    public ResponseEntity<Boolean> deductStock(
            @PathVariable String id,
            @RequestParam int quantity) {

        boolean isDeducted = productService.deductStock(id, quantity);
        return ResponseEntity.ok(isDeducted);
    }
}