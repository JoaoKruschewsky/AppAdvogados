package com.example.Advogados.mapper;


import com.example.Advogados.domains.Requests;
import com.example.Advogados.domains.dto.RequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RequestMapper {



    Requests toRequest (RequestDTO requestDTO);
}
