package com.adidas.backend.prioritysaleservice.controller;

import org.springframework.http.ResponseEntity;

public interface EmailSenderRestController {
    ResponseEntity<String> postSendEmails(int winnerAmount);
}
