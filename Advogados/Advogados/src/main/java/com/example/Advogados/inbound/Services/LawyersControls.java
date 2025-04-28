package com.example.Advogados.Services;

import com.example.Advogados.Model.DTO.Lawyer.LawyerDTO;
import com.example.Advogados.Model.DTO.Lawyer.LoginLawyerDTO;
import com.example.Advogados.Model.Lawyers;
import org.springframework.http.ResponseEntity;

public interface LawyersControls {

    public ResponseEntity<?> controlsSaveLawyer(LawyerDTO lawyers);
    public ResponseEntity<?> controlsLoginLawyer(LoginLawyerDTO Lawyers);

}
