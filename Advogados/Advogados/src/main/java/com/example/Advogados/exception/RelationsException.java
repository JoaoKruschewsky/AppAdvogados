package com.example.Advogados.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class RelationsException extends RuntimeException{

   private HttpStatusCode code;

    public RelationsException(String message, HttpStatusCode code) {
        super(message);
        this.code = code;
    }

}
