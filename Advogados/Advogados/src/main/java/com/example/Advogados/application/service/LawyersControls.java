package com.example.Advogados.application.service;


import com.example.Advogados.domains.dto.Lawyer.LawyerDTO;
import com.example.Advogados.domains.dto.Lawyer.LoginLawyerDTO;
import com.example.Advogados.domains.dto.Lawyer.UpdateLawyerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface LawyersControls {

    public ResponseEntity<HttpStatus> saveLawyer(LawyerDTO lawyers);
    public ResponseEntity<HttpStatus> loginLawyer(LoginLawyerDTO Lawyers);
    public ResponseEntity<HttpStatus> updateLawyer(Long id, UpdateLawyerDTO body, JwtAuthenticationToken token);

}
