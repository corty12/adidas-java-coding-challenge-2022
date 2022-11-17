package com.adidas.backend.publicservice.service;

import com.adidas.backend.publicservice.exception.InvalidEmailException;
import com.adidas.backend.publicservice.model.QueueSubscriptionBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.regex.Pattern;


@Slf4j
@Service
@RequiredArgsConstructor
public class QueueSubscriptionService {

    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";
    private final KafkaTemplate<String, QueueSubscriptionBean> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    private static boolean validEmail(String pEmail) {
        return Pattern.compile(EMAIL_PATTERN)
                .matcher(pEmail)
                .matches();
    }

    private void sendMessage(QueueSubscriptionBean pQueueSubscriptionBean) {
        ListenableFuture<SendResult<String, QueueSubscriptionBean>> future = kafkaTemplate.send(topicName, pQueueSubscriptionBean);
        // TODO: gestionar onFailure (retries?)
        // TODO: future.get()
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

    public void subscribe(QueueSubscriptionBean pQueueSubscriptionBean)
            throws InvalidEmailException {
        log.info("Processing subscription for {}", pQueueSubscriptionBean);

        if (!validEmail(pQueueSubscriptionBean.getEmail().toString())) {
            throw new InvalidEmailException("Invalid Email", HttpStatus.BAD_REQUEST);
        }
        sendMessage(pQueueSubscriptionBean);
    }
}
