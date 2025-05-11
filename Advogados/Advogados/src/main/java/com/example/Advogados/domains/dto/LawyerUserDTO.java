package com.example.Advogados.domains.dto;

import com.example.Advogados.domains.dto.Lawyer.LawyerDTO;
import com.example.Advogados.domains.dto.User.UserDto;

public record LawyerUserDTO(LawyerDTO lawyerDTO, UserDto userDTO) {
}
