package com.example.api_gateway.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping("/productServiceFallback")
    public String productServiceFallback() {
        return "Product Service is taking too long to respond or is down. Please try again later.";
    }

    @RequestMapping("/orderServiceFallback")
    public String orderServiceFallback() {
        return "Order Service is currently unavailable. Our engineers are looking into it!";
    }
}
