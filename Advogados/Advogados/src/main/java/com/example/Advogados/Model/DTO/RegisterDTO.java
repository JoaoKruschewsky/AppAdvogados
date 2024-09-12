package com.example.Advogados.Model.DTO;

import com.example.Advogados.Model.DTO.Lawyer.LawyerDTO;
import com.example.Advogados.Model.DTO.User.UserDto;

public record RegisterDTO(LawyerDTO lawyerDTO, UserDto userDto) {
}
