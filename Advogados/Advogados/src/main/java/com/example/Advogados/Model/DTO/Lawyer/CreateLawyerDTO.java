package com.example.Advogados.Model.DTO.Lawyer;

import com.example.Advogados.Model.Lawyers;
import lombok.Getter;

@Getter
public record LawyerDTO(String name, String cpf, String email, String phoneNumber, String password) {
}
