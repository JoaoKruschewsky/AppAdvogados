package com.example.Advogados.Services.interfaces.lawyer;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.DTO.Lawyer.updateLawyerDTO;

public interface uptadeLawyer {

    public ResponseEntity<?> updateLawyer(Long id, updateLawyerDTO updateDTO);
}
