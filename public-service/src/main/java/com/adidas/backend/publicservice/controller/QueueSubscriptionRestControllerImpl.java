package com.adidas.backend.publicservice.controller;

import com.adidas.backend.avro.model.QueueSubscriptionBean;
import com.adidas.backend.publicservice.controller.dto.QueueSubscriptionDto;
import com.adidas.backend.publicservice.exception.InvalidEmailException;
import com.adidas.backend.publicservice.service.QueueSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/queue-subscription")
@RequiredArgsConstructor
public class QueueSubscriptionRestControllerImpl implements QueueSubscriptionRestController {

    private static final String SUBSCRIBED_MESSAGE_RESPONSE = "You have been successfully subscribed to the sale queue. Good luck!";
    private final QueueSubscriptionService mQueueSubscriptionService;

    @Override
    @PostMapping
    public ResponseEntity<String> postQueueSubscription(@Valid @RequestBody QueueSubscriptionDto pQueueSubscriptionDto) {

        QueueSubscriptionBean bean = QueueSubscriptionBean.newBuilder().setEmail(pQueueSubscriptionDto.getEmail()).build();

        try {
            mQueueSubscriptionService.subscribe(bean);
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
