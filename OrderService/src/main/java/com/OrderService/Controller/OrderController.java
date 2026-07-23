package com.OrderService.Controller;



import com.OrderService.Dto.OrderRequest;
import com.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        String responseMessage = orderService.placeOrder(orderRequest);

        if (responseMessage.contains("successfully")) {
            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }
}