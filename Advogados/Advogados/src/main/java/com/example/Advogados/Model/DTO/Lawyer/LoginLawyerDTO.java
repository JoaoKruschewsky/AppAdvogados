package com.example.Advogados.Model.DTO.Lawyer;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginLawyerDTO {

    private String emailDTO;
    @Size(min = 8)
    private String passwordDTO;

}
