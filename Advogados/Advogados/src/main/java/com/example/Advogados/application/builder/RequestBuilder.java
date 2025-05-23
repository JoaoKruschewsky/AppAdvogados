package com.example.Advogados.application.builder;


import com.example.Advogados.domains.Requests;
import com.example.Advogados.domains.response.RelationShipUsersResponse;
import com.example.Advogados.domains.response.RequestResponse;

import java.time.LocalDate;
import java.util.List;

public class RequestBuilder {

    public static List<RequestResponse> requestBuilder(List<Requests> body) {


        RelationShipUsersResponse builderLawyer = new RelationShipUsersResponse();
        builderLawyer.setId(Long.parseLong(body.stream().map(b -> b.getLawyer().getId()).toString()));
        builderLawyer.setName(body.stream().map(b -> b.getLawyer().getName()).toString());

        RelationShipUsersResponse builderUser = new RelationShipUsersResponse();
        builderUser.setId(Long.parseLong(body.stream().map(b -> b.getUser().getId()).toString()));
        builderUser.setName(body.stream().map(b -> b.getUser().getName()).toString());

        List<LocalDate> date = body.stream().map(Requests::getDateCreateRequests).toList();

        RequestResponse response = new RequestResponse();
        response.setLawyers(builderLawyer);
        response.setUsers(builderUser);
        response.setStatus(body.stream().map(Requests::getStatus).toString());
        response.setChangeRelation(body.stream().map(Requests::getChangeRelation).toString());
        response.setDateCreateRequests(date.get(0));

        return List.of(response);



    }
}
