package com.adidas.backend.prioritysaleservice.service;

import com.adidas.backend.prioritysaleservice.manager.SubscriptionsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final SubscriptionsManager subscriptionsManager;

    @Value("${kafka.topic.name}")
    private String topicName;

    public void sendEmails(int pEmailAmount) {
        List<String> winners = subscriptionsManager.findLuckyWinners(pEmailAmount);
        for (String winner : winners) {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, winner);
            // TODO: gestionar onFailure (retries?)
            // TODO: future.get()
            future.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("Sent message=[{}] offset=[{}]", pEmailAmount, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.error("Unable to send message=[" + pEmailAmount + "]: ", ex);
                }
            });
        }
    }
}
