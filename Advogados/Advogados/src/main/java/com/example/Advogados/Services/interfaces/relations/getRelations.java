package com.example.Advogados.Services.interfaces.relations;

import org.springframework.http.ResponseEntity;

public interface GetRelations {
    
    public ResponseEntity<?> ReadUser(final Long id);

    public ResponseEntity<?> ReadLawyer(final Long id);
}
