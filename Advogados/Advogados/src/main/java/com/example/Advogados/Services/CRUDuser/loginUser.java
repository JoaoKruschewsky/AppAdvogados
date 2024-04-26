package com.example.Advogados.Services.CRUDuser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.User.loginUserInterface;
import com.example.Advogados.message.message;

@Service
public class loginUser implements loginUserInterface{

    private repositoryUser actionUser;
    private message msg;

    @Autowired
    public void setWired(repositoryUser actionUser, message msg) {
        this.actionUser = actionUser;
        this.msg = msg;
    }


    public ResponseEntity<?> verifyLoginUser(User user) {
        Optional<User> existingUser = actionUser.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.get().getPassword().equals(user.getPassword())) {
            msg.setMensagem("login aceito Usuario.");
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            msg.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

    }
}
