package com.adidas.backend.prioritysaleservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SubscriptionsConsumer {

    @KafkaListener(topics = "subscriptions", groupId = "subscriptions")
    public void listenGroupSubscriptions(String message) {
        log.info("message received {}", message);
        //TODO: process message, send to adiclub mongo manager
    }
}