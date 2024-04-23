package com.example.Advogados.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.updateDTO;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.message.message;

@Service
public class servicesUser {

    @Autowired
    repositoryUser action;

    @Autowired
    message message;

    public ResponseEntity<?> saveUser(User user, BindingResult result) {
        Optional<User> verifyUser = action.findByEmail(user.getEmail());
        User verifyphoneNumber = action.findByphoneNumber(user.getPhoneNumber());
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            message.setMensagem(errorMessage.toString());
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (verifyUser != null) {
            message.setMensagem("Já existe um cliente com esse email cadastrado");

            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (verifyphoneNumber != null) {
            message.setMensagem("Número de Telefone cadastrado");

            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        } else {
            message.setMensagem("Nenhum usuario encontrado, Cadastro Aceito " + action.save(user));

            return new ResponseEntity<>(message, HttpStatus.OK);

        }
    }

    public ResponseEntity<?> verifyUser(User user) {
        Optional<User> existingUser = action.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.get().getPassword().equals(user.getPassword())) {
            message.setMensagem("login aceito Usuario.");
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            message.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

    }

}
