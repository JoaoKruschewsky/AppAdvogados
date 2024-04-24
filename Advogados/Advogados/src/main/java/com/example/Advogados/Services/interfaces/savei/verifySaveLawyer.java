package com.example.Advogados.Services.interfaces.savei;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.Lawyers;

public interface verifySaveLawyer {
    
    public ResponseEntity<?> verifySaveLawyer(Lawyers lawyers);
}
