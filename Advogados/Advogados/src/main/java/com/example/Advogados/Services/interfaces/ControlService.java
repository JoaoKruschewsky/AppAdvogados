package com.example.Advogados.Services.interfaces;

import com.example.Advogados.Model.DTO.LawyerUserDTO;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.RegisterDTO;
import com.example.Advogados.Model.UserAndLawyer;
import org.springframework.http.ResponseEntity;

public interface ControlService {


    public  ResponseEntity<?> verifySave(LawyerUserDTO lawyerUserDTO);
}
