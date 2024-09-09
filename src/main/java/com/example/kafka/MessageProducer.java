package com.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String,String> template;

    public void sendMessage(String msg) {
        getTemplate().send("Orders", msg);
    }

    public void sendMessageWithCallBack(String msg) {
        CompletableFuture<SendResult<String, String>> future = getTemplate().send("Orders", msg);
        future.whenComplete((result,ex) ->{
            if (ex == null) {
                System.out.println("Sent message=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        msg + "] due to : " + ex.getMessage());
            }
        });
    }

    public KafkaTemplate<String, String> getTemplate() {
        return template;
    }

    public void setTemplate(KafkaTemplate<String, String> template) {
        this.template = template;
    }
}
