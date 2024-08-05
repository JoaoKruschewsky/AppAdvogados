package com.example.Advogados.Services.interfaces.requests;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Services.interfaces.relations.GetRelations;

public interface GetRequests {
    public ResponseEntity<?> readUser(final long id);
}
