/**
 * 
 */
package com.amit.kafkaspring.config;

/**
 * 
 */

import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorConfig {

	@Bean
	public DefaultErrorHandler errorHandler(KafkaTemplate<String, String> template) {

		// WHERE to put failed messages: orders-dlt, same partition number
		DeadLetterPublishingRecoverer sendToDlt = new DeadLetterPublishingRecoverer(template,
                (record, ex) -> new TopicPartition(record.topic() + "-dlt", record.partition()));

		// HOW to retry: wait 1 second, retry 2 times, then send to DLT
		return new DefaultErrorHandler(sendToDlt, new FixedBackOff(1000L, 2));
	}
}