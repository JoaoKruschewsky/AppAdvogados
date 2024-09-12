package com.example.Advogados.Model.DTO.User;


import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record UserDto(String name,
                      @CPF  String cpf,
                      @Email String email,
                      String phoneNumber,
                      String password) {
}
