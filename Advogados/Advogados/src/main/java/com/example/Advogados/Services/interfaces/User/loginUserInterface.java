package com.example.Advogados.Services.interfaces.User;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.User;

public interface loginUserInterface {


    public ResponseEntity<?> verifyLoginUser(User user);
}
