package com.example.Advogados.mapper;

import com.example.Advogados.Model.DTO.LawyerUserDTO;
import com.example.Advogados.Model.UserAndLawyer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface LawyerMapper {


    @Mapping(source = "lawyerDTO" , target = "lawyer")
    UserAndLawyer toLawyer (LawyerUserDTO lawyerUserDTO);
}
