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
import com.example.Advogados.Model.updateDTO;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.message.message;

@Service
public class serviceLawyers {

    @Autowired
    repositoryLawyers action;

    @Autowired
    message message;

    public ResponseEntity<?> saveLawyers(Lawyers lawyers, BindingResult result) {
        Lawyers verifyUser = action.findByEmail(lawyers.getEmail());
        Lawyers verifyphoneNumber = action.findByphoneNumber(lawyers.getPhoneNumber());
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
            message.setMensagem("Nenhum usuario encontrado, Cadastro Aceito " + action.save(lawyers));

            return new ResponseEntity<>(message, HttpStatus.OK);

        }
    }

    public ResponseEntity<?> verifyLawyers(Lawyers Lawyers) {
        Lawyers existingUser = action.findByEmail(Lawyers.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(Lawyers.getPassword())) {
            message.setMensagem("login aceito Advogado.");
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            message.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> uptade(Long ID, updateDTO updateDTO) {
        Optional<Lawyers> optionalUser = action.findById(ID);
        if (optionalUser.isPresent()) {
            Lawyers Lawyers = optionalUser.get();
            Lawyers.setDescricion(updateDTO.getDescricionDTO());
            Lawyers.setImg_Profile(updateDTO.getImgDTO());
            Lawyers.setPrice(updateDTO.getPriceDTO());
            return new ResponseEntity<>(action.save(Lawyers), HttpStatus.OK);
        } else {
            message.setMensagem("Ocorreu um erro tente novamente");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

    }
}
