package com.example.Advogados.Services.interfaces.relations;

import org.springframework.http.ResponseEntity;

public interface getRelations {
    
    public ResponseEntity<?> getRelationShip(final Long id);
}
