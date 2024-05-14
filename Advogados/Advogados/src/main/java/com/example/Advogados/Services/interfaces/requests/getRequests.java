package com.example.Advogados.Services.interfaces.requests;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Services.interfaces.relations.getRelations;

public interface getRequests {
    public ResponseEntity<?> readUser(final long id);
}
