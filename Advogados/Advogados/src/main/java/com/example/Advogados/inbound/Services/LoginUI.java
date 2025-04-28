package com.example.Advogados.Services;

import com.example.Advogados.Model.UserAndLawyer;
import com.example.Advogados.Model.DTO.LoginDTO;

public interface LoginUI {

    public UserAndLawyer verifyLogin(LoginDTO user);
}
