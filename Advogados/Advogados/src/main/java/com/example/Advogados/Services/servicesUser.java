package com.example.Advogados.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.message.message;

@Service
public class servicesUser {

    @Autowired
    repositoryUser action;

    @Autowired
    message message;

    public ResponseEntity<?> saveUser(User user) {
        User verifyUser = action.findByEmail(user.getEmail());
        User verifyCPF = action.findBycpf(user.getCPF());
        User verifyphoneNumber = action.findByphoneNumber(user.getPhoneNumber());
        if (verifyUser != null) {
            message.setMensagem("Já existe um cliente com esse email cadastrado");

            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (verifyCPF != null) {
            message.setMensagem("Já existe uma Pessoa com CPF cadastrado");

            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        } else if (verifyphoneNumber != null) {
            message.setMensagem("Número de Telefone cadastrado");

            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        } else {
            message.setMensagem("Nenhum usuario encontrado, Cadastro Aceito " + action.save(user));

            return new ResponseEntity<>(message, HttpStatus.OK);

        }
    }

}
