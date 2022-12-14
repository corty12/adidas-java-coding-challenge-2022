package com.adidas.backend.prioritysaleservice.listener;

import com.adidas.backend.avro.model.QueueSubscriptionBean;
import com.adidas.backend.prioritysaleservice.manager.SubscriptionsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionsConsumer {

    private final SubscriptionsManager subscriptionsManager;

    @KafkaListener(topics = "subscriptions", groupId = "subscriptions")
    public void subscriptionsListener(QueueSubscriptionBean queueSubscriptionBean) {
        log.info("Received new subscription! {}", queueSubscriptionBean);
        subscriptionsManager.processNewSubscription(queueSubscriptionBean);
    }
}
