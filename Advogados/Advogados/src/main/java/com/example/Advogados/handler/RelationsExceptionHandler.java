package com.example.Advogados.handler;


import com.example.Advogados.exception.MessageException;
import com.example.Advogados.exception.RelationsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RelationsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RelationsException.class)
    public ResponseEntity<MessageException> existsRelations (RelationsException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
               new MessageException(
                       "This user already has a relationship",
                       HttpStatus.NOT_ACCEPTABLE
               )
        );

    }
}
