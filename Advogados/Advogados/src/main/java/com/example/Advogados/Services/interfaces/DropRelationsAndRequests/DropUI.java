package com.example.Advogados.Services.interfaces.DropRelationsAndRequests;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public interface DropUI {

    public ResponseEntity<HttpStatusCode>  dropAllById(final List<Long> id);
}
