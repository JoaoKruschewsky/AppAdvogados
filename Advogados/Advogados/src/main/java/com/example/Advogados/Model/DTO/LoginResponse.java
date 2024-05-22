package com.example.Advogados.Model.DTO;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;

public record LoginResponse(String acessToken, Long expiresIn, User user, Lawyers lawyers) {

}
