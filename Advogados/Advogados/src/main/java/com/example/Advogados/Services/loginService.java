package com.example.Advogados.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.message.message;

@Service
public class loginService {

    private repositoryLawyers actionLawyers;
    private repositoryUser actionUser;
    private message msg;

    @Autowired
    public void setWired(repositoryLawyers actionLawyers, repositoryUser actionUser, message msg) {
        this.actionLawyers = actionLawyers;
        this.actionUser = actionUser;
        this.msg = msg;
    }

    // Valida Advogado
    public ResponseEntity<?> verifyLawyers(Lawyers Lawyers) {
        Optional<Lawyers> existingLawyers = actionLawyers.findByEmail(Lawyers.getEmail());

        if (existingLawyers != null && existingLawyers.get().getPassword().equals(Lawyers.getPassword())) {
            msg.setMensagem("login aceito Advogado.");
            return new ResponseEntity<>(existingLawyers, HttpStatus.OK);
        } else {
            msg.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    // Valida Usuario
    public ResponseEntity<?> verifyUser(User user) {
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
