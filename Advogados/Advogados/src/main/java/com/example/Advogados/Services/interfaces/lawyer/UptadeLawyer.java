package com.example.Advogados.Services.interfaces.lawyer;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.example.Advogados.Model.DTO.Lawyer.UpdateLawyerDTO;

public interface UptadeLawyer {

    public ResponseEntity<?> updateLawyer(Long id, UpdateLawyerDTO updateDTO, JwtAuthenticationToken token);
}
