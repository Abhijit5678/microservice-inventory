package com.example.notificationservice.notificationservice.Consumer;




import com.example.notificationservice.notificationservice.Dto.OrderPlacedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderPlacedConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderPlacedConsumer.class);

    @KafkaListener(topics = "order-placed-topic", groupId = "notification-group")
    public void consumeOrderDetails(OrderPlacedEvent event) {

        log.info("============== NEW KAFKA MESSAGE RECEIVED ==============");
        log.info("Processing notification for Order ID: {}", event.getOrderId());
        log.info("Sending confirmation email to Customer: {}", event.getCustomerId());

        // In real life production: you would inject JavaMailSender here
        // and send an actual email via SMTP or AWS SES.

        log.info("Notification successfully delivered for order: {}", event.getOrderId());
        log.info("========================================================");
    }
}