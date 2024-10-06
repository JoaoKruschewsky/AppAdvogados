package com.example.Advogados.Services.interfaces.requests;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Services.interfaces.relations.GetRelations;

import java.util.List;

public interface GetRequests {
    public List<Object> readUser(final long id);
}
