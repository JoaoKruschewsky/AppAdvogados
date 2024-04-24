package com.example.Advogados.Services.registerService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.savei.verifySaveUser;
import com.example.Advogados.message.message;

@Service
public class saveUser implements verifySaveUser{
    private repositoryUser actionUser;
    private message msg;

    @Autowired
    public void setWired( repositoryUser actionUser, message msg) {
        this.actionUser = actionUser;
        this.msg = msg;
    }
    public ResponseEntity<?> verifyUser (User user){
        Optional<User> verifyUser = actionUser.findByEmail(user.getEmail());
        // User verifyphoneNumber = actionUser.findByphoneNumber(user.getPhoneNumber());
        if (verifyUser.get().getEmail() != user.getEmail()) {
            msg.setMensagem("Já existe um email cadastrado");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } else {
            msg.setMensagem("Nenhum usuario encontrado, Cadastro Aceito " + actionUser.save(user));

            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }
}
