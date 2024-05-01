package com.example.Advogados.Services.interfaces.User;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.DTO.User.LoginUserDTO;

public interface loginUserInterface {

    public ResponseEntity<?> verifyLoginUser(LoginUserDTO user);
}
