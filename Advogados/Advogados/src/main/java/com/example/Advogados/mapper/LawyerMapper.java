package com.example.Advogados.mapper;

import com.example.Advogados.Model.DTO.Lawyer.LawyerDTO;
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


    Lawyers toLawyer (LawyerDTO body);


    void updateLaywer(LawyerDTO updateLawyerDTO, @MappingTarget Lawyers lawyers);

}
