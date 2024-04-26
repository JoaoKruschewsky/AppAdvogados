package com.example.Advogados.Services.interfaces.validation;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;

public interface validationI {

    public ResponseEntity<?> validation(User user);
}
