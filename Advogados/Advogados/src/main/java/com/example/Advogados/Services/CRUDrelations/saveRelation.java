package com.example.Advogados.Services.CRUDrelations;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.Services.interfaces.relations.SaveRelationUI;
import com.example.Advogados.message.Message;

@Service
public class SaveRelation implements SaveRelationUI {

    private RepositoryRelationShip action;
    private RepositoryUser actionUser;
    private RepositoryLawyers actionLawyer;
    private Message msg;

    @Autowired
    public void setWired(RepositoryRelationShip action, RepositoryUser actionUser, RepositoryLawyers actionLawyers,
            Message msg) {
        this.action = action;
        this.actionUser = actionUser;
        this.actionLawyer = actionLawyers;
        this.msg = msg;
    }

    @Override
    public ResponseEntity<?> saveNewRelation(LawyerClientRelationship relation, JwtAuthenticationToken token) {
        Optional<Lawyers> existingLawyer = actionLawyer.findById(relation.getLawyer().getId());

        Optional<LawyerClientRelationship> existingRelation = action
                .findLawyerClientRelationshipByClientIdAndLawyerId(relation.getClient().getId(),
                        relation.getLawyer().getId());

        if (!relation.getClient().getId().equals(Long.parseLong(token.getName()))) {
            msg.setMensagem("Voce nao pode salvar relacoes apenas Usuarios!");
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);

        } else if (!existingLawyer.isPresent()) {
            msg.setMensagem("Nao existe esse advogado!");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } else if (existingRelation.isPresent()) {
            msg.setMensagem("Você já tem uma relação com esse advogado");
            return new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
        } else {
            action.save(relation);
            msg.setMensagem("Relacao salva");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> saveUpdateRelation(final LawyerClientRelationship relation) {
        Optional<Lawyers> existingLawyer = actionLawyer.findById(relation.getLawyer().getId());

        if (!existingLawyer.isPresent()) {
            msg.setMensagem("Nao existe esse advogado!");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } else {
            action.save(relation);
            msg.setMensagem("Relacao salva");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }

}
