package com.example.Advogados.Services.interfaces.login;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.Lawyers;

public interface verifyLoginLawyer  {

    
    public ResponseEntity<?> verifySaveLawyers(Lawyers Lawyers);
}
