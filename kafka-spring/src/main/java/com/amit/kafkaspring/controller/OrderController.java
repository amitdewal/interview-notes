/**
 * 
 */
package com.amit.kafkaspring.controller;

/**
 * 
 */


import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amit.kafkaspring.model.Order;

@RestController
public class OrderController {

    private final KafkaTemplate<String, Order> kafkaTemplate;   // value is now an Order object

    public OrderController(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/orders")
    public Order placeOrder(@RequestParam String customer,
                            @RequestParam String item,
                            @RequestParam double price) {

        Order order = new Order(UUID.randomUUID().toString(), customer, item, price);

        kafkaTemplate.send("orders-json", order.customer(), order)   // key = customer
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("SENT -> " + order
                                + " | partition=" + result.getRecordMetadata().partition()
                                + " | offset=" + result.getRecordMetadata().offset());
                    } else {
                        System.out.println("FAILED: " + ex.getMessage());
                    }
                });

        return order;
    }
}