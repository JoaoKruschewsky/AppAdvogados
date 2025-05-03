package com.example.Advogados.domains.response;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data

public class RelationShipResponse{

    private List<RelationShipUsersResponse> lawyerResponse;
    private List<RelationShipUsersResponse> userResponse;
    private String status;
    private LocalDate dateCreateRelation;
}
