package com.example.Advogados.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.message.message;

@Service
public class saveService {
    private repositoryLawyers actionLawyers;
    private repositoryUser actionUser;
    private message msg;

    @Autowired
    public void setWired(repositoryLawyers actionLawyers, repositoryUser actionUser, message msg) {
        this.actionLawyers = actionLawyers;
        this.actionUser = actionUser;
        this.msg = msg;
    }

    public ResponseEntity<?> saveLawyers(Lawyers lawyers) {
        Optional<Lawyers> verifyLawyers = actionLawyers.findByEmail(lawyers.getEmail());
        // Lawyers verifyphoneNumber =
        // actionLawyers.findByphoneNumber(lawyers.getPhoneNumber());
        if (verifyLawyers.get().getEmail() != lawyers.getEmail()) {
            msg.setMensagem("Já existe um email cadastrado");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } else {
            msg.setMensagem("Nenhum usuario encontrado, Cadastro Aceito " + actionLawyers.save(lawyers));

            return new ResponseEntity<>(msg, HttpStatus.OK);
        }

    }

    public ResponseEntity<?> saveUser(User user) {
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
