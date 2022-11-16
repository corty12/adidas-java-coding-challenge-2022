package com.adidas.backend.publicservice.service;

import com.adidas.backend.publicservice.model.QueueSubscriptionBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Slf4j
@Service
public class QueueSubscriptionService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    private void sendMessage(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, msg);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + msg + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[" + msg + "] due to : " + ex.getMessage());
            }
        });

    }

    public void subscribe(QueueSubscriptionBean pQueueSubscriptionBean) {
        log.info("Processing subscription for {}", pQueueSubscriptionBean);
        sendMessage("Processing subscription for " + pQueueSubscriptionBean);
    }
}
