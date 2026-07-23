package com.OrderService.Repository;


import com.OrderService.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // Inherits  MongoDB CRUD methods effortlessly
}
