package com.example.Advogados.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.relation.Relation;

import org.json.JSONObject;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryRequests;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.message.message;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import jakarta.validation.constraints.NotNull;

@Service
public class serviceRequests {
    @Autowired
    private repositoryUser actionUser;

    @Autowired
    private repositoryLawyers actionLawyer;

    @Autowired
    private repositoryRequests action;

    @Autowired
    private repositoryRelationShip actionRelation;

    @Autowired
    private message msg;

    public ResponseEntity<?> saveRequests(Requests requests) {

        Optional<User> existingUser = actionUser.findById(requests.getUsers().getId());
        Optional<Lawyers> existingLawyer = actionLawyer.findById(requests.getLawyers().getId());

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

    public ResponseEntity<?> getRequestsUser(long id) {
        List<Requests> user = action.findRequestsByUserId(id);
        if (!user.isEmpty()) {
            ArrayList<Object> names = new ArrayList<>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(user);
                JsonNode rootNode = objectMapper.readTree(json);

                for (JsonNode node : rootNode) {

                    JsonNode lawyerNode = node.get("lawyers");
                    JsonNode lawyerStatus = node.get("status");
                    if (lawyerStatus.asText().equalsIgnoreCase("pendente")) {
                        names.add(lawyerNode.get("name").asText());
                        names.add(lawyerStatus.asText());
                        names.add(node.get("changeRelation").asText());
                        names.add(lawyerNode.get("id").asInt());

                    } else {
                        // names.add(lawyerNode.get("name").asText() + " e " + lawyerStatus.asText());
                    }

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

    public ResponseEntity<?> saveSecondRequests(final Requests requests) {

        List<Requests> existingRequestsUser = action.findRequestsByUserId(requests.getUsers().getId());

        if (!existingRequestsUser.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(existingRequestsUser);
                JsonNode rootNode = objectMapper.readTree(json);

                for (JsonNode node : rootNode) {

                    JsonNode lawyerNode = node.get("lawyers");
                    JsonNode lawyerStatus = node.get("status");
                    JsonNode relation = node.get("changeRelation");
                    int i = lawyerNode.get("id").asInt();
                    if (i != requests.getLawyers().getId()) {

                        continue;

                    } else if (!lawyerStatus.asText().equalsIgnoreCase("pendente")) {

                        msg.setMensagem("Essa solicitacao foi: " + lawyerStatus.asText());
                        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
                    } else {

                        LawyerClientRelationship relationShip = new LawyerClientRelationship();

                        relationShip.setLawyer(requests.getLawyers());
                        relationShip.setClient(requests.getUsers());
                        relationShip.setStatus(requests.getChangeRelation());
                        actionRelation.save(relationShip);
                        action.save(requests);
                        msg.setMensagem("Solicitação de " + relation.asText() +
                                " salva com sucesso.");
                    }
                }

                return new ResponseEntity<>(msg, HttpStatus.OK);

            } catch (JsonProcessingException e) {

                e.printStackTrace();
                msg.setMensagem("User não encontrado");
                return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else

        {
            msg.setMensagem("Não foi possível encontrar o Usuario ");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
