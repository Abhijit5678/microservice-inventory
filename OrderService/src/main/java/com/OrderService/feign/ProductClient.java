package com.OrderService.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", url = "http://localhost:8081/api/products")
public interface ProductClient {

    @PutMapping("/{id}/deduct")
    ResponseEntity<Boolean> deductStock(
            @PathVariable("id") String id,
            @RequestParam("quantity") int quantity
    );
}