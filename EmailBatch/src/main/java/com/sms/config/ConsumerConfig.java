package com.sms.config;

import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

//@Configuration
//@EnableKafka
public class ConsumerConfig {
	

/**	@Bean
	public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
	        ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
	        ConsumerFactory<Object, Object> kafkaConsumerFactory) {

	    ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
	    configurer.configure(factory, kafkaConsumerFactory);

	    factory.setBatchListener(true);

	    return factory;
	}
**/
}
