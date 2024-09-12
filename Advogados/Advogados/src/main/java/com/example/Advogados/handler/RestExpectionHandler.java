/* package com.example.Advogados.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExpectionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e) {

        ValidationError validation = new ValidationError();
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            validation.addErr(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(e.getStatusCode()).body(validation);
    }

}*/
