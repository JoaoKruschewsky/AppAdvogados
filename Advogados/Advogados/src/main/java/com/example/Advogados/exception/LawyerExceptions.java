package com.example.Advogados.exception;

import org.springframework.http.HttpStatus;

public class LawyerExceptions extends  RuntimeException{

    private HttpStatus id;

    public LawyerExceptions(String message, HttpStatus id) {

        super(message);
        this.id = id;
    }
}
