package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import java.util.concurrent.CountDownLatch;


public class MessageConsumer {

    private CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topicPartitions = @TopicPartition(topic = "Orders", partitions = { "0", "1", "2" }))
   // @KafkaListener(topics = "Orders", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
        getLatch().countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
