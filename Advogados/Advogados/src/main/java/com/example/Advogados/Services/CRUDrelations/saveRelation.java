package com.example.Advogados.Services.CRUDrelations;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.relations.saveRelationUI;
import com.example.Advogados.message.Message;

@Service
public class saveRelation implements saveRelationUI {

    private repositoryRelationShip action;
    private repositoryUser actionUser;
    private repositoryLawyers actionLawyer;
    private Message msg;

    @Autowired
    public void setWired(repositoryRelationShip action, repositoryUser actionUser, repositoryLawyers actionLawyers,
            Message msg) {
        this.action = action;
        this.actionUser = actionUser;
        this.actionLawyer = actionLawyers;
        this.msg = msg;
    }

    @Override
    public ResponseEntity<?> saveNewRelation(LawyerClientRelationship relation) {
        Optional<User> existingUser = actionUser.findById(relation.getClient().getId());
        Optional<Lawyers> existingLawyer = actionLawyer.findById(relation.getLawyer().getId());
        if (existingUser.isPresent() && existingLawyer.isPresent()) {
            ArrayList<Object> saves = new ArrayList<>();
            saves.add(existingUser);
            saves.add(existingLawyer);
            action.save(relation);
            msg.setMensagem("Relacao salva entre " + saves);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            msg.setMensagem("Não foi possível salvar a relação");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

}
