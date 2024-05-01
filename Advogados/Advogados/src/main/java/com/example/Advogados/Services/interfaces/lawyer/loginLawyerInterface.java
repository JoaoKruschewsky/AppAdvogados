package com.example.Advogados.Services.interfaces.lawyer;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.DTO.Lawyer.LoginLawyerDTO;

public interface loginLawyerInterface {

    public ResponseEntity<?> verifyLoginLawyers(LoginLawyerDTO Lawyers);
}
