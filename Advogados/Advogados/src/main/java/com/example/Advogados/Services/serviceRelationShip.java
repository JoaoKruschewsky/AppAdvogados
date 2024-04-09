package com.example.Advogados.Services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.message.message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.read.ListAppender;

@Service
public class serviceRelationShip {

    @Autowired
    private repositoryRelationShip action;

    @Autowired
    private message msg;

    public ResponseEntity<?> getRelationsUser(long id) {

        List<LawyerClientRelationship> user = action.findAllLawyerClientRelationshipsByClientId(id);
        if (!user.isEmpty()) {
            ArrayList<String> names = new ArrayList<>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(user);
                JsonNode rootNode = objectMapper.readTree(json);

                for (JsonNode node : rootNode) {

                    JsonNode lawyerNode = node.get("lawyer");
                    JsonNode lawyerStatus = node.get("status");
                    names.add(lawyerNode.get("name").asText());
                    names.add(lawyerStatus.asText());

                }
                return new ResponseEntity<>(names, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                // Trate a exceção aqui
                e.printStackTrace(); // ou qualquer outra forma de tratamento
                msg.setMensagem("User não encontrado");
                return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            msg.setMensagem("Não existe usuário com esse nome");
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getRelationsLawyer(long id) {

        List<LawyerClientRelationship> user = action.findAllLawyerClientRelationshipsByLawyerId(id);
        if (!user.isEmpty()) {
            ArrayList<String> names = new ArrayList<>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(user);
                JsonNode rootNode = objectMapper.readTree(json);

                for (JsonNode node : rootNode) {

                    JsonNode lawyerNode = node.get("client");
                    JsonNode lawyerStatus = node.get("status");
                    names.add(lawyerNode.get("name").asText());
                    names.add(lawyerStatus.asText());

                }
                return new ResponseEntity<>(names, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                // Trate a exceção aqui
                e.printStackTrace(); // ou qualquer outra forma de tratamento
                msg.setMensagem("User não encontrado");
                return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            msg.setMensagem("Não existe usuário com esse nome");
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }
}
