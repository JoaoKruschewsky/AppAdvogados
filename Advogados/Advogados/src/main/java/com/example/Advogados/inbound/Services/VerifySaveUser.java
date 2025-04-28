package com.example.Advogados.Services;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.User;

public interface VerifySaveUser {
   
    public ResponseEntity<?> verifyUser(User user);
}
