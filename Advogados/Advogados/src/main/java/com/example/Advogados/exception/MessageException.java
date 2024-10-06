package com.example.Advogados.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class MessageException {

    private String message;
    private HttpStatus status;
}
