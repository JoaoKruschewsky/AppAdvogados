package com.example.Advogados.Services.CRUDrelations;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.apache.tomcat.util.json.JSONFilter;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
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
import com.example.Advogados.message.message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.read.ListAppender;
import lombok.extern.java.Log;

@Service
public class saveRelation {

    
    private repositoryRelationShip action;
    private repositoryUser actionUser;
    private repositoryLawyers actionLawyer;
    private message msg;

    @Autowired
    public void setWired(repositoryRelationShip action, repositoryUser actionUser, repositoryLawyers actionLawyers, message msg){
        this.action = action;
        this.actionUser = actionUser;
        this.actionLawyer = actionLawyers;
        this.msg = msg;
    }

    public ResponseEntity<?> saveRelation(LawyerClientRelationship relation) {
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
