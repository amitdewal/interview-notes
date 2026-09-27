///**
// * 
// */
//package com.amit.kafka;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.Properties;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//
///**
// * 
// */
//public class OrderConsumer {
//	public static void main(String[] args) throws InterruptedException {
//
//		// setting 1
//		Properties props = new Properties();
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////		props.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-service"); // which TEAM
//		props.put(ConsumerConfig.GROUP_ID_CONFIG, "sms-service");
//		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // DON'T save bookmark automatically
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // no bookmark? start from line 0
//
//		// 2. Create the consumer
//		// 2. Create the consumer
//		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
//
//			// 3. Tell Kafka which topic this team reads
//			consumer.subscribe(List.of("orders"));
//
////			// 4. Keep asking Kafka: "any new messages?"
////			while (true) {
////				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
////
////				for (ConsumerRecord<String, String> r : records) {
////					System.out.println("GOT -> key=" + r.key() + " | value=" + r.value() + " | partition="
////							+ r.partition() + " | offset=" + r.offset());
////				}
////			}
//
//	/*		while (true) {
//				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
//
//				// 1. PROCESS the messages (for us: just print)
//				for (ConsumerRecord<String, String> r : records) {
//					System.out.println("GOT -> key=" + r.key() + " | value=" + r.value() + " | partition="
//							+ r.partition() + " | offset=" + r.offset());
//				}
//
//				// 2. SAVE the bookmark, only AFTER processing
////				if (!records.isEmpty()) {
////					consumer.commitSync();
////					System.out.println("COMMITTED -> bookmark saved for " + records.count() + " messages");
////				}
//				
//                if (!records.isEmpty()) {
//                    System.out.println("PROCESSED. Saving bookmark in 10 seconds... (press STOP now to crash!)");
//                    Thread.sleep(10000);   // the dangerous gap
//                    consumer.commitSync();
//                    System.out.println("COMMITTED -> bookmark saved for " + records.count() + " messages");
//                }
//			}
//			*/
//			
//			
//            while (true) {
//                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
//
//                if (!records.isEmpty()) {
//
//                    // 1. Just SHOW what we received (not processed yet)
//                    for (ConsumerRecord<String, String> r : records) {
//                        System.out.println("RECEIVED -> key=" + r.key()
//                                + " | partition=" + r.partition()
//                                + " | offset=" + r.offset());
//                    }
//
//                    // 2. SAVE the bookmark FIRST
//                    consumer.commitSync();
//                    System.out.println("COMMITTED FIRST -> bookmark saved for " + records.count() + " messages");
//
//                    // 3. The dangerous gap
//                    System.out.println("Processing in 10 seconds... (press STOP now to crash!)");
//                    Thread.sleep(10000);
//
//                    // 4. PROCESS (in real life: charge money / send SMS)
//                    for (ConsumerRecord<String, String> r : records) {
//                        System.out.println("PROCESSED -> key=" + r.key()
//                                + " | value=" + r.value()
//                                + " | offset=" + r.offset());
//                    }
//                }
//            }
//		}
//
//	}
//
//}

package com.amit.kafka;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class OrderConsumer {

    public static void main(String[] args) throws Exception {

        // Our "database" of already-processed order IDs (survives crashes)
        Path db = Path.of("processed-orders.txt");
        Set<String> processed = new HashSet<>(Files.exists(db) ? Files.readAllLines(db) : List.of());
        System.out.println("Loaded " + processed.size() + " already-processed orders from DB");

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9094,localhost:9095");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "billing-service");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(List.of("orders"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) continue;

                for (ConsumerRecord<String, String> r : records) {
                    String orderId = r.value().split(" ")[0];   // e.g. "order-1727330000000-0"

                    // 1. CHECK: already processed?
                    if (processed.contains(orderId)) {
                        System.out.println("SKIPPED (duplicate) -> " + orderId
                                + " | partition=" + r.partition() + " | offset=" + r.offset());
                        continue;
                    }

                    // 2. PROCESS (charge money)
                    System.out.println("CHARGED -> key=" + r.key() + " | " + orderId
                            + " | partition=" + r.partition() + " | offset=" + r.offset());

                    // 3. REMEMBER it in the DB
                    processed.add(orderId);
                    Files.writeString(db, orderId + System.lineSeparator(),
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }

                // 4. The dangerous gap, then COMMIT
                System.out.println("PROCESSED. Saving bookmark in 10 seconds... (press STOP now to crash!)");
                Thread.sleep(10000);
                consumer.commitSync();
                System.out.println("COMMITTED");
            }
        }
    }
}
