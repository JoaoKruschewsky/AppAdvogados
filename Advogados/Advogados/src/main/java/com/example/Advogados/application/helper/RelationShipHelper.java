package com.example.Advogados.application.helper;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.application.builder.RelationshipBuilder;
import com.example.Advogados.domains.response.RelationShipResponse;
import com.example.Advogados.domains.response.RelationShipUsersResponse;
import org.h2.store.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
