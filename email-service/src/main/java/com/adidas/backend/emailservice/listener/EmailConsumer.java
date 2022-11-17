package com.adidas.backend.emailservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailConsumer {


    @KafkaListener(topics = "emails", groupId = "emails")
    public void emailListener(String message) {
        log.info("Sending winner email to {}", message);

        //Would send email here...
    }
}
