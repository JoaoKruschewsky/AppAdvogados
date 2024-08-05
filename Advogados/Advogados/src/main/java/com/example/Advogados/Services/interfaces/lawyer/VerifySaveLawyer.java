package com.example.Advogados.Services.interfaces.lawyer;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.Lawyers;

public interface VerifySaveLawyer {
    
    public ResponseEntity<?> verifySaveLawyer(Lawyers lawyers);
}
