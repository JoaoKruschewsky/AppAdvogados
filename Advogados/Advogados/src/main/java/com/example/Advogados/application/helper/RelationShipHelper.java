package com.example.Advogados.application.helper;

import com.example.Advogados.application.builder.RelationshipBuilder;
import com.example.Advogados.domains.LawyerClientRelationship;
import com.example.Advogados.domains.response.RelationShipResponse;


import java.util.List;
import java.util.NoSuchElementException;

public class RelationShipHelper {

    public static List<RelationShipResponse> veirifyIfExistsRelationLawyers(List<LawyerClientRelationship> relationShip) {

        if (relationShip.isEmpty()){
            throw new NoSuchElementException();
        }

        return RelationshipBuilder.parseRelationShipLawyers(relationShip);
    }
    public static List<RelationShipResponse> veirifyIfExistsRelationUsers(List<LawyerClientRelationship> relationShip) {

        if (relationShip.isEmpty()){
            throw new NoSuchElementException();
        }

        return RelationshipBuilder.parseRelationShipLawyers(relationShip);
    }
}
