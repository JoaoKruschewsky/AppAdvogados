package com.example.Advogados.Services.Impl;

import com.example.Advogados.Model.DTO.Lawyer.LawyerDTO;
import com.example.Advogados.Model.DTO.Lawyer.LoginLawyerDTO;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Services.LawyersControls;
import com.example.Advogados.exception.LawyerExceptions;
import com.example.Advogados.mapper.LawyerMapper;
import com.example.Advogados.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@Service

public class LawyersControlsImpl implements LawyersControls {

    private final RepositoryLawyers actionLawyers;
    private final Message msg;
    private final LawyerMapper lawyerMapper;

    public LawyersControlsImpl(RepositoryLawyers actionLawyers, Message msg, LawyerMapper lawyerMapper) {
        this.actionLawyers = actionLawyers;
        this.msg = msg;
        this.lawyerMapper = lawyerMapper;
    }

    @Override
    public ResponseEntity<?> controlsSaveLawyer(LawyerDTO lawyers) {
       if(actionLawyers.findById(lawyers.id()).isPresent()){
           throw new LawyerExceptions("Not found Lawyer", HttpStatus.NOT_FOUND);
       };

       actionLawyers.save(lawyerMapper.toLawyer(lawyers));

    return ResponseEntity.ok().build();

    }


    @Override
    public ResponseEntity<?> controlsLoginLawyer(LoginLawyerDTO Lawyers) {
        Optional<Lawyers> existingLawyers = actionLawyers.findByEmail(Lawyers.getEmailDTO());

        if (existingLawyers.isPresent() && existingLawyers.get().getPassword().equals(Lawyers.getPasswordDTO())) {
            msg.setMensagem("login aceito Advogado.");
            return new ResponseEntity<>(existingLawyers, HttpStatus.OK);
        } else {
            msg.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }


    }
}
