package com.example.Advogados.Services.interfaces.login;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.User;

public interface verifyLoginUser {


    public ResponseEntity<?> verifyLoginUser(User user);
}
