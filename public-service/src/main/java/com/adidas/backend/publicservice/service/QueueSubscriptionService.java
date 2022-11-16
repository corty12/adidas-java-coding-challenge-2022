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
    private KafkaTemplate<String, QueueSubscriptionBean> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    private void sendMessage(QueueSubscriptionBean pQueueSubscriptionBean) {
        ListenableFuture<SendResult<String, QueueSubscriptionBean>> future = kafkaTemplate.send(topicName, pQueueSubscriptionBean);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, QueueSubscriptionBean> result) {
                log.info("Sent message=[{}] offset=[{}]", pQueueSubscriptionBean, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[" + pQueueSubscriptionBean + "]: ", ex);
            }
        });

    }

    public void subscribe(QueueSubscriptionBean pQueueSubscriptionBean) {
        log.info("Processing subscription for {}", pQueueSubscriptionBean);
        sendMessage(pQueueSubscriptionBean);
    }
}
