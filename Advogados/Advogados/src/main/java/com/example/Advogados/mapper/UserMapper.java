package com.example.Advogados.mapper;


import com.example.Advogados.domains.User;
import com.example.Advogados.domains.dto.User.UpdateUserDTO;
import com.example.Advogados.domains.dto.User.UserDto;
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
