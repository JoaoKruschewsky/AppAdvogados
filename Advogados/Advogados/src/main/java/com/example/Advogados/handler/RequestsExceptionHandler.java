package com.example.Advogados.handler;


import com.example.Advogados.exception.MessageException;
import com.example.Advogados.exception.RequestsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RequestsExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RequestsException.class)
    public ResponseEntity<MessageException> resquestLimits (RequestsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new MessageException(
                        "Request limit exceeded",
                        HttpStatus.CONFLICT
                )
        );
    }
}
