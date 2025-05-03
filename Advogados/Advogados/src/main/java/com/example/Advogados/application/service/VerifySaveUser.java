package com.example.Advogados.application.service;

import com.example.Advogados.Model.User;
import org.springframework.http.ResponseEntity;

public interface VerifySaveUser {
   
    public ResponseEntity<?> verifyUser(User user);
}
