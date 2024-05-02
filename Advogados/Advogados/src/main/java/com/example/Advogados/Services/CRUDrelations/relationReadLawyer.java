package com.example.Advogados.Services.CRUDrelations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Enum.RelationEnum;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Services.interfaces.relations.getRelations;
import com.example.Advogados.message.message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class relationReadLawyer implements getRelations {

    private repositoryRelationShip action;
    private message msg;

    @Autowired
    public void setWired(repositoryRelationShip action, message msg) {
        this.action = action;
        this.msg = msg;

    }

    public ResponseEntity<?> getRelationShip(final Long id) {
        List<LawyerClientRelationship> Lawyer = action.findAllLawyerClientRelationshipsByLawyerId(id);
        RelationEnum emAndamento = RelationEnum.Em_Andamento;
        if (!Lawyer.isEmpty()) {
            ArrayList<Object> names = new ArrayList<>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(Lawyer);
                JsonNode rootNode = objectMapper.readTree(json);

                for (JsonNode node : rootNode) {

                    JsonNode lawyerNode = node.get("client");
                    JsonNode lawyerStatus = node.get("status");

                    names.add(lawyerNode.get("name").asText());
                    names.add(lawyerStatus.asText());
                    names.add(lawyerNode.get("id"));

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
            return new ResponseEntity<>(Lawyer, HttpStatus.BAD_REQUEST);
        }
    }
}
