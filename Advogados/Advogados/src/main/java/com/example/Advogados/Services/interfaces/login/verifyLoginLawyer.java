package com.example.Advogados.Services.interfaces.login;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;

public interface verifyLoginLawyer {

    public ResponseEntity<?> verifySaveLawyers(Lawyers Lawyers);
}
