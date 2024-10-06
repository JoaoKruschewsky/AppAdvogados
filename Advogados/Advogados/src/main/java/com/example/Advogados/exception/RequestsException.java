package com.example.Advogados.exception;

import org.springframework.http.HttpStatus;

public class RequestsException extends RuntimeException{


    public RequestsException(String message) {
        super(message);

    }
}
