package com.example.Advogados.application.exception;

import org.springframework.http.HttpStatus;

public class RequestsException extends RuntimeException{


    private final HttpStatus code;

    public RequestsException(String message, HttpStatus code) {
        super(message);
        this.code = code;

    }
}
