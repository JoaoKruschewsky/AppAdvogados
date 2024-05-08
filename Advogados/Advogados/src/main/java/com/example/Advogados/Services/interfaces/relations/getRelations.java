package com.example.Advogados.Services.interfaces.relations;

import org.springframework.http.ResponseEntity;

public interface getRelations {
    
    public ResponseEntity<?> ReadUser(final Long id);

    public ResponseEntity<?> ReadLawyer(final Long id);
}
