package com.example.Advogados.domains.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserDTO {

    private String emailDTO;
    private String phoneNumberDTO;
    private String imgDTO;

}
