package com.example.Advogados.exception;

import lombok.RequiredArgsConstructor;

public class RelationsException extends RuntimeException{



    public RelationsException(String message, String error) {
        super(message);

    }

}
