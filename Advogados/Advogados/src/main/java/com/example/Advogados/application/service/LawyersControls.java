package com.example.Advogados.application.service;

import com.example.Advogados.Model.DTO.Lawyer.LawyerDTO;
import com.example.Advogados.Model.DTO.Lawyer.LoginLawyerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface LawyersControls {

    public ResponseEntity<?> saveLawyer(LawyerDTO lawyers);
    public ResponseEntity<?> loginLawyer(LoginLawyerDTO Lawyers);
    public ResponseEntity<?> updateLawyer(Long id, LawyerDTO body, JwtAuthenticationToken token);

}
