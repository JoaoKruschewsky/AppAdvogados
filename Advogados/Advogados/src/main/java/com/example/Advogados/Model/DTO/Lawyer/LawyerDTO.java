package com.example.Advogados.Model.DTO.Lawyer;

import com.example.Advogados.Model.Lawyers;
import lombok.Getter;


public record LawyerDTO(Long id, String name, String cpf, String email, String phoneNumber, String password) {
}
