package com.example.Advogados.Services.CRUDrequests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Repository.repositoryRequests;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.requests.getRequests;
import com.example.Advogados.message.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReadRequests implements getRequests {
    private repositoryRequests action;
    private Message msg;

    @Autowired
    public void setWired(repositoryRequests action, Message msg) {
        this.action = action;
        this.msg = msg;

    }

    @Override
    public ResponseEntity<?> readUser(final long id) {
        List<Requests> user = action.findRequestsByUserId(id);
        if (!user.isEmpty()) {
            ArrayList<Object> names = new ArrayList<>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(user);
                JsonNode rootNode = objectMapper.readTree(json);

                for (JsonNode node : rootNode) {

                    JsonNode lawyerNode = node.get("lawyer");
                    JsonNode lawyerStatus = node.get("status");

                    names.add(node.get("id"));
                    names.add(lawyerNode.get("name").asText());
                    names.add(lawyerStatus.asText());
                    names.add(node.get("changeRelation").asText());
                    names.add(lawyerNode.get("id").asInt());

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
