package com.example.Advogados.application.exception;

import org.springframework.http.HttpStatusCode;

public class RelationsException extends RuntimeException{

   private final HttpStatusCode code;

    public RelationsException(String message, HttpStatusCode code) {
        super(message);
        this.code = code;
    }

}
