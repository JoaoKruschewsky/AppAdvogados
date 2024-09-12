package com.example.Advogados.Services.interfaces;

import com.example.Advogados.Model.DTO.LawyerUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface PostUser {

    ResponseEntity<HttpStatus> saveLawyer (LawyerUserDTO lawyerUserDTO);


    ResponseEntity<HttpStatus> saveUser (LawyerUserDTO lawyerUserDTO);
}
