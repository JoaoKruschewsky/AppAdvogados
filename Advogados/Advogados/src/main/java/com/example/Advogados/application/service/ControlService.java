package com.example.Advogados.application.service;

import com.example.Advogados.Model.DTO.LawyerUserDTO;
import org.springframework.http.ResponseEntity;

public interface ControlService {


    public  ResponseEntity<?> verifySave(LawyerUserDTO lawyerUserDTO);
}
