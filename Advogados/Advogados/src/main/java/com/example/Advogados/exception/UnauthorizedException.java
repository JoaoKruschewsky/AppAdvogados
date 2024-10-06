package com.example.Advogados.exception;

public class UnauthorizedException extends RuntimeException{
    private final String error;

    public UnauthorizedException(String message, String error) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
