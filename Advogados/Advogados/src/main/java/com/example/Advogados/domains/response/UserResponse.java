package com.example.Advogados.domains.response;


import lombok.Data;

@Data
public class UserResponse {

    private long id;
    private String cpf;
    private String name;
    private String phoneNumber;
    private String email;
}
