package com.ProductService.ProductService.Repository;



import com.ProductService.ProductService.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // Basic database operations are inherited automatically.
    // If you need custom queries later (e.g., findByName), you define them here.
}
