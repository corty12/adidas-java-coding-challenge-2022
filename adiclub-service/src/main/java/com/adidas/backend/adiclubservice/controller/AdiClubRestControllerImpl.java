package com.adidas.backend.adiclubservice.controller;

import com.adidas.backend.adiclubservice.dto.AdiClubMemberInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@RestController
@RequestMapping(value = "/adiclub")
public class AdiClubRestControllerImpl implements AdiClubRestController {
    private static final Random RANDOM = new Random(System.nanoTime());

    @Override
    @GetMapping
    public ResponseEntity<AdiClubMemberInfoDto> getAdiClubMemberInfo(@RequestParam(value = "emailAddress") final String emailAddress) {

        return ResponseEntity
                .ok()
                .body(AdiClubMemberInfoDto
                        .builder()
                        .email(emailAddress)
                        .points(RANDOM.nextInt(20)) //reduced for testing purposes
                        .registrationDate(Instant.now().minus(RANDOM.nextInt(365), ChronoUnit.DAYS))
                        .build()
                );
    }
}
