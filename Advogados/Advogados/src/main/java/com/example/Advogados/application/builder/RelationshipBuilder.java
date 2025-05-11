package com.example.Advogados.application.builder;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.domains.response.RelationShipResponse;
import com.example.Advogados.domains.response.RelationShipUsersResponse;

import java.time.LocalDate;
import java.util.List;

public class    RelationshipBuilder {


    public static List<RelationShipResponse> parseRelationShipLawyers (List<LawyerClientRelationship> relationShip){


        RelationShipUsersResponse lawyerResponse = new RelationShipUsersResponse();
        lawyerResponse.setName(relationShip.stream().map(l -> l.getLawyer().getName()).toString());
        lawyerResponse.setId(Long.parseLong(relationShip.stream().map(l -> l.getLawyer().getId()).toString()));

        RelationShipUsersResponse usersResponse = new RelationShipUsersResponse();
        usersResponse.setName(relationShip.stream().map(l -> l.getClient().getName()).toString());
        usersResponse.setId(Long.parseLong(relationShip.stream().map(l -> l.getClient().getId()).toString()));

        List<LocalDate> date = relationShip.stream().map(LawyerClientRelationship::getDateCreateRelation).toList();

        RelationShipResponse response = new RelationShipResponse();
        response.setLawyerResponse(lawyerResponse);
        response.setUserResponse(usersResponse);
        response.setDateCreateRelation(date.get(0));
        response.setStatus(relationShip.stream().map(LawyerClientRelationship::getStatus).toString());

        return List.of(response);

    }
    public static List<RelationShipResponse> parseRelationShipUsers(List<LawyerClientRelationship> relationShip){


        RelationShipUsersResponse lawyerResponse = new RelationShipUsersResponse();
        lawyerResponse.setName(relationShip.stream().map(l -> l.getLawyer().getName()).toString());
        lawyerResponse.setId(Long.parseLong(relationShip.stream().map(l -> l.getLawyer().getId()).toString()));

        RelationShipUsersResponse usersResponse = new RelationShipUsersResponse();
        usersResponse.setName(relationShip.stream().map(l -> l.getClient().getName()).toString());
        usersResponse.setId(Long.parseLong(relationShip.stream().map(l -> l.getClient().getId()).toString()));

        List<LocalDate> date = relationShip.stream().map(LawyerClientRelationship::getDateCreateRelation).toList();

        RelationShipResponse response = new RelationShipResponse();
        response.setLawyerResponse(lawyerResponse);
        response.setUserResponse(usersResponse);
        response.setDateCreateRelation(date.get(0));
        response.setStatus(relationShip.stream().map(LawyerClientRelationship::getStatus).toString());

        return List.of(response);

    }
}
