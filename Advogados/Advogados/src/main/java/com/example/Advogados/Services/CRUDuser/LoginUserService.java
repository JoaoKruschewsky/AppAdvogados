package com.example.Advogados.Services.CRUDuser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.DTO.User.LoginUserDTO;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.User.loginUserInterface;
import com.example.Advogados.message.Message;

@Service
public class LoginUserService implements loginUserInterface {

    private repositoryUser actionUser;
    private Message msg;

    @Autowired
    public void setWired(repositoryUser actionUser, Message msg) {
        this.actionUser = actionUser;
        this.msg = msg;
    }

    @Override
    public ResponseEntity<?> verifyLoginUser(LoginUserDTO user) {
        Optional<User> existingUser = actionUser.findByEmail(user.getEmailDTO());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPasswordDTO())) {
            // msg.setMensagem("login aceito Usuario.");
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            // msg.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
