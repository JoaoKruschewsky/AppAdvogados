package com.example.Advogados.Model.DTO;

import com.example.Advogados.Model.DTO.Lawyer.LawyerDTO;
import com.example.Advogados.Model.DTO.User.UserDto;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;

public record LawyerUserDTO(LawyerDTO lawyerDTO,  UserDto userDTO) {
}
