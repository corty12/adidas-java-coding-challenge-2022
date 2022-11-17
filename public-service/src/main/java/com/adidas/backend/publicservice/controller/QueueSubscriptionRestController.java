package com.adidas.backend.publicservice.controller;

import com.adidas.backend.publicservice.controller.dto.QueueSubscriptionDto;
import org.springframework.http.ResponseEntity;

public interface QueueSubscriptionRestController {
    ResponseEntity<String> postQueueSubscription(QueueSubscriptionDto pQueueSubscriptionDto);
}
