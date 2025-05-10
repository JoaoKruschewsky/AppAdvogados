package com.example.Advogados.domains.response;


import com.example.Advogados.Model.Lawyers;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestResponse {

    private LawyerResponse lawyers;
    private UserResponse users;
    private String changeRelation;
    private String status;
    private LocalDate dateCreateRequests;

}
