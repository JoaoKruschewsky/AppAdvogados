package com.example.Advogados.mapper;

import com.example.Advogados.Model.DTO.Lawyer.UpdateLawyerDTO;
import com.example.Advogados.Model.DTO.LawyerUserDTO;
import com.example.Advogados.Model.DTO.User.UpdateUserDTO;
import com.example.Advogados.Model.DTO.User.UserDto;
import com.example.Advogados.Model.User;
import com.example.Advogados.Model.UserAndLawyer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper  {


    User toUser (UserDto lawyerUserDTO);


    void updateUser(UpdateUserDTO updateUserDTO, @MappingTarget User user);

}
