package com.example.Advogados.Services.interfaces.User;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.User.LoginUserDTO;

public interface loginUserInterface {

    public User verifyLoginUser(LoginDTO user);
}
