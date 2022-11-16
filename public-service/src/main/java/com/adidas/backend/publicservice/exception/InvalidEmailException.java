package com.adidas.backend.publicservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class InvalidEmailException extends Exception {
    private final String message;
    private final HttpStatus httpStatus;
}
