package com.example.Advogados.Services.interfaces.savei;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.User;

public interface verifySaveUser {
   
    public ResponseEntity<?> verifyUser(User user);
}
