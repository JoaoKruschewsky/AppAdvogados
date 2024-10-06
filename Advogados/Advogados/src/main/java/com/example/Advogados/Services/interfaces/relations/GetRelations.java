package com.example.Advogados.Services.interfaces.relations;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GetRelations {
    
    public List<Object> ReadUser(final Long id);

    public List<Object> ReadLawyer(final Long id);
}