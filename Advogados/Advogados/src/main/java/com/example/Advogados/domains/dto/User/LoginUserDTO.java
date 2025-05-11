package com.example.Advogados.domains.dto.User;

import com.example.Advogados.domains.dto.Lawyer.LoginLawyerDTO;

public class LoginUserDTO extends LoginLawyerDTO {


    public LoginUserDTO(String email, String password) {
        super(email, password);
    }

}
