package com.example.Advogados.mapper;

import com.example.Advogados.Model.DTO.LawyerUserDTO;
import com.example.Advogados.Model.DTO.User.UserDto;
import com.example.Advogados.Model.User;
import com.example.Advogados.Model.UserAndLawyer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper  {


    @Mapping(source = "userDTO" , target = "user")
    UserAndLawyer toUser (LawyerUserDTO lawyerUserDTO);



}
