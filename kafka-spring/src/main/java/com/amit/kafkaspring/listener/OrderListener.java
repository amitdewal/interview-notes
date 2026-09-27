/**
 * 
 */
package com.amit.kafkaspring.listener;

/**
 * 
 */
//package com.amit.kafkaspring;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.amit.kafkaspring.model.Order;

@Component
public class OrderListener {

    @KafkaListener(topics = "orders-json")
    public void onOrder(ConsumerRecord<String, Order> r) {
        Order order = r.value();   // already an Order object, no parsing needed

        System.out.println("GOT -> customer=" + order.customer()
                + " | item=" + order.item()
                + " | price=" + order.price()
                + " | partition=" + r.partition()
                + " | offset=" + r.offset());

        if ("poison".equals(order.item())) {
            throw new RuntimeException("Cannot process this order!");
        }
        System.out.println("PROCESSED OK -> " + order.orderId());
    }

    @KafkaListener(topics = "orders-json-dlt", groupId = "dlt-checker")
    public void onFailedOrder(ConsumerRecord<String, Order> r) {
        var errorHeader = r.headers().lastHeader("kafka_dlt-exception-message");
        String error = (errorHeader != null) ? new String(errorHeader.value()) : "unknown";
        System.out.println("📦 DLT GOT -> " + r.value() + " | reason=" + error);
    }
}