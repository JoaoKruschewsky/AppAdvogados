package com.example.Advogados.Services;

import com.example.Advogados.Model.DTO.LawyerUserDTO;
import org.springframework.http.ResponseEntity;

public interface ControlService {


    public  ResponseEntity<?> verifySave(LawyerUserDTO lawyerUserDTO);
}
