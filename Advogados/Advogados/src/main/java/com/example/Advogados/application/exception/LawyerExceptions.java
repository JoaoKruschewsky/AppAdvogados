package com.example.Advogados.application.exception;

import org.springframework.http.HttpStatus;

public class LawyerExceptions extends  RuntimeException{

    private final HttpStatus id;

    public LawyerExceptions(String message, HttpStatus id) {

        super(message);
        this.id = id;
    }
}
