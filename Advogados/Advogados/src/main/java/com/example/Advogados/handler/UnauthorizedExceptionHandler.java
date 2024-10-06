package com.example.Advogados.handler;


import com.example.Advogados.exception.MessageException;
import com.example.Advogados.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnauthorizedExceptionHandler {


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<MessageException> unauthorizedError(UnauthorizedException e) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new MessageException(
                "not permission, verify .",
                HttpStatus.UNAUTHORIZED
        )
        );
    }

}
