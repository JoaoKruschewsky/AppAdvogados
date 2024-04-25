package com.example.Advogados.Services.interfaces.update;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.updateDTO;

public interface uptadeLawyer {
    
    public ResponseEntity<?> updateLawyer(Long id, updateDTO updateDTO);
}
