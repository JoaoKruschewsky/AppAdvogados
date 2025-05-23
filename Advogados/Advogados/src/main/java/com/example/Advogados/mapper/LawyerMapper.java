package com.example.Advogados.mapper;

import com.example.Advogados.domains.Lawyers;
import com.example.Advogados.domains.dto.Lawyer.LawyerDTO;
import com.example.Advogados.domains.dto.Lawyer.UpdateLawyerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LawyerMapper {


    Lawyers toLawyer (LawyerDTO body);


    void updateLaywer(UpdateLawyerDTO updateLawyerDTO, @MappingTarget Lawyers lawyers);

}
