package com.example.Advogados.Services.loginService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Services.interfaces.login.verifyLoginLawyer;
import com.example.Advogados.message.message;

@Service
public class loginLawyer implements verifyLoginLawyer {
    private repositoryLawyers actionLawyers;
    private message msg;

    @Autowired
    public void setWired(repositoryLawyers actionLawyers, message msg) {
        this.actionLawyers = actionLawyers;
        this.msg = msg;
    }

    public ResponseEntity<?> verifySaveLawyers(Lawyers Lawyers) {
        Optional<Lawyers> existingLawyers = actionLawyers.findByEmail(Lawyers.getEmail());

        if (existingLawyers != null && existingLawyers.get().getPassword().equals(Lawyers.getPassword())) {
            msg.setMensagem("login aceito Advogado.");
            return new ResponseEntity<>(existingLawyers, HttpStatus.OK);
        } else {
            msg.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

}
