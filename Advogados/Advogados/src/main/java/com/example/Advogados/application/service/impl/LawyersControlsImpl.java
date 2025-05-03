package com.example.Advogados.application.service.impl;

import com.example.Advogados.Model.DTO.Lawyer.LawyerDTO;
import com.example.Advogados.Model.DTO.Lawyer.LoginLawyerDTO;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.application.service.LawyersControls;
import com.example.Advogados.exception.LawyerExceptions;
import com.example.Advogados.mapper.LawyerMapper;
import com.example.Advogados.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
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
    public ResponseEntity<?> saveLawyer(LawyerDTO lawyers) {
       if(actionLawyers.findById(lawyers.id()).isPresent()){
           throw new LawyerExceptions("Not found Lawyer", HttpStatus.NOT_FOUND);
       };
       actionLawyers.save(lawyerMapper.toLawyer(lawyers));

       return ResponseEntity.ok().build();

    }


    @Override
    public ResponseEntity<?> loginLawyer(LoginLawyerDTO Lawyers) {
        Optional<Lawyers> existingLawyers = actionLawyers.findByEmail(Lawyers.getEmailDTO());

        if (!(existingLawyers.isPresent() && existingLawyers.get().getPassword().equals(Lawyers.getPasswordDTO()))) {
            throw  new ResponseStatusException( HttpStatus.UNAUTHORIZED, "Unregistered user or invalid credentials");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updateLawyer(Long id, LawyerDTO body, JwtAuthenticationToken token) {
        Optional<Lawyers> optionalLawyer = actionLawyers.findById(id);

        if (optionalLawyer.isEmpty()) {
            throw new NoSuchElementException(" No found Lawyer by id");
        }

        if (id.equals(Long.parseLong(token.getName()))) {
            lawyerMapper.updateLaywer(body, optionalLawyer.get());
            actionLawyers.save(optionalLawyer.get());
            return ResponseEntity.ok().build();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
