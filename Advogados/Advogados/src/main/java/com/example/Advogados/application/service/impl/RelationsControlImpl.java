package com.example.Advogados.application.service.impl;


import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.application.service.RelationsControl;
import com.example.Advogados.domains.response.RelationShipResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RelationsControlImpl implements RelationsControl {

    private final RepositoryRelationShip action;

    public RelationsControlImpl(RepositoryRelationShip action) {
        this.action = action;
    }

    @Override
    public ResponseEntity<?> saveRelation(LawyerClientRelationship relation, JwtAuthenticationToken token) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateRelation(LawyerClientRelationship relation) {
        return null;
    }

    @Override
    public List<RelationShipResponse> getRelations(long id) {

        List<LawyerClientRelationship> user = action.findRelationshipUser(id);
        List<LawyerClientRelationship> lawyer = action.findRelationshipLawyer(id);

        if (user.isEmpty() ||  lawyer.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "There are no relations");
        }



        return List.of();
    }
}
