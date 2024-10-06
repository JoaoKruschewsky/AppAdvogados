package com.example.Advogados.Services.CRUDlawyer;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Model.DTO.Lawyer.LoginLawyerDTO;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Services.interfaces.lawyer.LoginLawyerInterface;
import com.example.Advogados.message.Message;

@Service
@AllArgsConstructor
public class LoginLawyer implements LoginLawyerInterface {
    private final RepositoryLawyers actionLawyers;
    private final Message msg;

    @Override
    public ResponseEntity<?> verifyLoginLawyers(LoginLawyerDTO Lawyers) {
        Optional<Lawyers> existingLawyers = actionLawyers.findByEmail(Lawyers.getEmailDTO());

        if (existingLawyers != null && existingLawyers.get().getPassword().equals(Lawyers.getPasswordDTO())) {
            msg.setMensagem("login aceito Advogado.");
            return new ResponseEntity<>(existingLawyers, HttpStatus.OK);
        } else {
            msg.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

}