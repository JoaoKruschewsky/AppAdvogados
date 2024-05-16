package com.example.Advogados.Services.CRUDrequests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.Requests;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Repository.repositoryRequests;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.requests.SavesRequests;
import com.example.Advogados.message.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CreatedRequests implements SavesRequests {

    private repositoryUser actionUser;
    private repositoryLawyers actionLawyer;
    private repositoryRequests action;
    private repositoryRelationShip actionRelation;
    private Message msg;

    @Autowired
    public void setWired(repositoryUser actionUser, repositoryLawyers actionLawyers, repositoryRequests action,
            repositoryRelationShip actionRelation, Message msg) {
        this.actionUser = actionUser;
        this.actionLawyer = actionLawyers;
        this.action = action;
        this.actionRelation = actionRelation;
        this.msg = msg;

    }

    @Override
    public ResponseEntity<?> saveRequests(final Requests requests) {

        Optional<User> existingUser = actionUser.findById(requests.getUser().getId());
        Optional<Lawyers> existingLawyer = actionLawyer.findById(requests.getLawyer().getId());

        if (existingUser.isPresent() && existingLawyer.isPresent()) {
            ArrayList<Object> saves = new ArrayList<>();
            saves.add(existingUser);
            saves.add(existingLawyer);
            saves.add(requests);
            action.save(requests);

            return new ResponseEntity<>(saves, HttpStatus.OK);
        } else {
            msg.setMensagem("Não foi possível salvar a relação");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> saveSecondRequests(final Requests requests) {

        Optional<User> existingUser = actionUser.findById(requests.getUser().getId());
        Optional<Lawyers> existingLawyer = actionLawyer.findById(requests.getLawyer().getId());

        if (existingUser.isPresent() && existingLawyer.isPresent()) {
            ArrayList<Object> saves = new ArrayList<>();
            saves.add(existingUser);
            saves.add(existingLawyer);
            saves.add(requests);
            action.save(requests);

            return new ResponseEntity<>(saves, HttpStatus.OK);
        } else {
            msg.setMensagem("Não foi possível salvar a relação");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
