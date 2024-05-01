package com.example.Advogados.Model.DTO.User;

import com.example.Advogados.Model.DTO.Lawyer.LoginLawyerDTO;

public class LoginUserDTO extends LoginLawyerDTO {


    public LoginUserDTO(String email, String password) {
        super(email, password);
    }

}
