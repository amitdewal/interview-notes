/**
 * 
 */
package com.amit.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * 
 */
public class OrderProducer {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// 1. Settings: where Kafka is, and how to convert key/value to bytes
		Properties props = new Properties();
//		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9094,localhost:9095");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// 2. Create the producer (try-with-resources closes it at the end)
		try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
//			String key = "amit"; // KEY -> decides the partition
//			String value = "order-16 chai"; // VALUE -> the actual message

//			// 3. The message: which topic, key, value
//			ProducerRecord<String, String> record = new ProducerRecord<>("orders", key, value);
//
//			// 4. Send it, and print the "receipt" Kafka sends back
//
//			producer.send(record, (metadata, exception) -> {
//				if (exception == null) {
//					System.out.println("SENT -> key=" + key + " | partition=" + metadata.partition() + " | offset="
//							+ metadata.offset());
//				} else {
//					System.out.println("FAILED: " + exception.getMessage());
//				}
//			});
			
            String[] customers = {"amit", "rahul", "neha", "sneha", "vikas"};

            for (int i = 0; i < customers.length; i++) {
                String key = customers[i];                  // KEY
//                String value = "order-" + (20 + i) + " tea"; // VALUE
                String value = "order-" + System.currentTimeMillis() + "-" + i + " tea";

                ProducerRecord<String, String> record =
                        new ProducerRecord<>("orders", key, value);

                producer.send(record, (metadata, exception) -> {
                    if (exception == null) {
                        System.out.println("SENT -> key=" + key
                                + " | partition=" + metadata.partition()
                                + " | offset=" + metadata.offset());
                    } else {
                        System.out.println("FAILED: " + exception.getMessage());
                    }
                });
            }

		}

	}

}
