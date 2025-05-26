package com.example.Advogados.application.service.impl;



import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.application.helper.RelationShipHelper;
import com.example.Advogados.application.service.RelationsControl;
import com.example.Advogados.application.usecase.RelationShipUseCase;
import com.example.Advogados.domains.LawyerClientRelationship;
import com.example.Advogados.domains.dto.RelationShipDTO;
import com.example.Advogados.domains.dto.RelationShipUpdateDTO;
import com.example.Advogados.domains.response.RelationShipResponse;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<HttpStatus> drop(final List<Long> id) {
        action.deleteAllById(id);

        id.forEach(i -> {
            boolean exist = action.findById(i).isEmpty();

            if(exist) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu um erro");
            }
        });

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> saveRelation(RelationShipDTO relation, JwtAuthenticationToken token) {
        Optional<LawyerClientRelationship> existingRelation = action
                .findLawyerClientRelationshipByClientIdAndLawyerId(relation.idUser(), relation.idLawyer());

        RelationShipUseCase.validRelationShip(relation, token);
        if (existingRelation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        action.save(relation);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> updateRelation(RelationShipUpdateDTO relation) {


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
