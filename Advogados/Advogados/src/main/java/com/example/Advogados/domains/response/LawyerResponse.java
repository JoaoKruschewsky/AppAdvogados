package com.example.Advogados.domains.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LawyerResponse {

    private long id;
    private String cpf;
    private String name;
    private String phoneNumber;
    private String email;
    private String titleLawyers;
    private String specializedAir;
    private String descricion;
    private BigDecimal price;



}
