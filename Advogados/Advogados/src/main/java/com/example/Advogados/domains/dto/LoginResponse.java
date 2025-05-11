package com.example.Advogados.domains.dto;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;

public record LoginResponse(String acessToken, Long expiresIn, User user, Lawyers lawyers) {

}
