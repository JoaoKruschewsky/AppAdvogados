package com.example.Advogados.application.service.impl;


import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.application.helper.RelationShipHelper;
import com.example.Advogados.application.service.RelationsControl;
import com.example.Advogados.application.usecase.RelationShipUseCase;
import com.example.Advogados.domains.response.RelationShipResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RelationsControlImpl implements RelationsControl {

    private final RepositoryRelationShip action;

    public RelationsControlImpl(RepositoryRelationShip action) {
        this.action = action;
    }

    @Override
    public ResponseEntity<?> saveRelation(LawyerClientRelationship relation, JwtAuthenticationToken token) {
        Optional<LawyerClientRelationship> existingRelation = action
                .findLawyerClientRelationshipByClientIdAndLawyerId(relation.getClient().getId(), relation.getLawyer().getId());

        RelationShipUseCase.validRelationShip(relation, token);
        if (existingRelation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        action.save(relation);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> updateRelation(LawyerClientRelationship relation) {


            action.save(relation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<RelationShipResponse> getRelations(final long id) {

        List<LawyerClientRelationship> user = action.findRelationshipUser(id);
        List<LawyerClientRelationship> lawyer = action.findRelationshipLawyer(id);

        RelationShipHelper.veirifyIfExistsRelationUsers(user);
        RelationShipHelper.veirifyIfExistsRelationLawyers(lawyer);


        return RelationShipHelper.veirifyIfExistsRelationUsers(user).isEmpty() ? RelationShipHelper.veirifyIfExistsRelationLawyers(lawyer) : RelationShipHelper.veirifyIfExistsRelationUsers(user);
    }
}
