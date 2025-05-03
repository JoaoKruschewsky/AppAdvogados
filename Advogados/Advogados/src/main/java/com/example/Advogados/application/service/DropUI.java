package com.example.Advogados.application.service;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DropUI {

    public ResponseEntity<HttpStatusCode>  dropAllById(final List<Long> id);
}
