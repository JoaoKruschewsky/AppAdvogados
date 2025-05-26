package com.example.Advogados.domains.response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestResponse {

    private RelationShipUsersResponse lawyers;
    private RelationShipUsersResponse users;
    private String changeRelation;
    private String status;
    private LocalDate dateCreateRequests;

}
