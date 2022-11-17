package com.adidas.backend.prioritysaleservice.controller;

import com.adidas.backend.prioritysaleservice.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/send-emails")
@RequiredArgsConstructor
public class EmailSenderRestControllerImpl implements EmailSenderRestController {

    private static final String SENT_EMAILS_MESSAGE_RESPONSE = "Sending emails to the lucky winners!";
    private final EmailSenderService emailSenderService;

    @Override
    @PostMapping
    public ResponseEntity<String> postSendEmails(@RequestParam(value = "winnerAmount") int winnerAmount) {

        emailSenderService.sendEmails(winnerAmount);

        return ResponseEntity
                .ok()
                .body(SENT_EMAILS_MESSAGE_RESPONSE);
    }
}
