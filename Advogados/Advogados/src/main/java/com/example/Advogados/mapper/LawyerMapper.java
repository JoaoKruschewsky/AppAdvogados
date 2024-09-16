package com.example.Advogados.mapper;

import com.example.Advogados.Model.DTO.Lawyer.UpdateLawyerDTO;
import com.example.Advogados.Model.DTO.LawyerUserDTO;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.UserAndLawyer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LawyerMapper {


    @Mapping(source = "lawyerDTO" , target = "lawyer")
    UserAndLawyer toLawyer (LawyerUserDTO lawyerUserDTO);


    void updateLaywer(UpdateLawyerDTO updateLawyerDTO, @MappingTarget Lawyers lawyers);

}
