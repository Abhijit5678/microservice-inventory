package com.OrderService.service;



import com.OrderService.Dto.OrderPlacedEvent;
import com.OrderService.Dto.OrderRequest;
import com.OrderService.Entity.Order;
import com.OrderService.Repository.OrderRepository;
import com.OrderService.feign.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {
        // 1. Sync Call via Feign to check and deduct inventory
        Boolean isStockDeducted = productClient.deductStock(
                orderRequest.getProductId(),
                orderRequest.getQuantity()
        ).getBody();

        if (Boolean.TRUE.equals(isStockDeducted)) {
            // 2. Map and Save Order document to MongoDB
            Order order = new Order();
            order.setId(UUID.randomUUID().toString());
            order.setCustomerId(orderRequest.getCustomerId());
            order.setProductId(orderRequest.getProductId());
            order.setQuantity(orderRequest.getQuantity());

            BigDecimal total = orderRequest.getPriceAtPurchase()
                    .multiply(BigDecimal.valueOf(orderRequest.getQuantity()));
            order.setTotalAmount(total);
            order.setStatus("CREATED");
            order.setOrderTime(LocalDateTime.now());

            orderRepository.save(order);

            // 3. Async Call via Kafka to broadcast the event
            OrderPlacedEvent event = new OrderPlacedEvent(order.getId(), order.getCustomerId());
            kafkaTemplate.send("order-placed-topic", event);

            return "Order placed successfully! Order ID: " + order.getId();
        } else {
            return "Order placement failed: Insufficient product stock or product not found.";
        }
    }
}
