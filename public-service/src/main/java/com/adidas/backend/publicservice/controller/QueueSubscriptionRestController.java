package com.adidas.backend.publicservice.controller;

import com.adidas.backend.publicservice.exception.InvalidEmailException;
import com.adidas.backend.publicservice.model.QueueSubscriptionBean;
import com.adidas.backend.publicservice.service.QueueSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/queue-subscription")
public class QueueSubscriptionRestController {

    private static final String SUBSCRIBED_MESSAGE_RESPONSE = "You have been successfully subscribed to the sale queue. Good luck!";

    @Autowired
    private QueueSubscriptionService mQueueSubscriptionService;

    @PostMapping
    public ResponseEntity<String> postQueueSubscription(@Valid @RequestBody QueueSubscriptionBean pQueueSubscriptionBean) {

        try {
            mQueueSubscriptionService.subscribe(pQueueSubscriptionBean);
        } catch (InvalidEmailException exception) {
            return ResponseEntity
                    .status(exception.getHttpStatus())
                    .body(exception.getMessage());
        }
        return ResponseEntity
                .ok()
                .body(SUBSCRIBED_MESSAGE_RESPONSE);
    }

}
