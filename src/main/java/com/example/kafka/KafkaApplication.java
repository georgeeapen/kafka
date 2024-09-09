package com.example.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;


@SpringBootApplication(scanBasePackages = {"com.example.kafka"})
public class KafkaApplication {


	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context  = SpringApplication.run(KafkaApplication.class, args);
	    MessageProducer producer = context.getBean(MessageProducer.class);
		MessageConsumer listener = context.getBean(MessageConsumer.class);

		producer.sendMessageWithCallBack("Order");

		listener.getLatch().await(10, TimeUnit.SECONDS);
	}

	@Bean
	public MessageProducer messageProducer() {
		return new MessageProducer();
	}

	@Bean
	public MessageConsumer messageConsumer() {
		return new MessageConsumer();
	}

}
