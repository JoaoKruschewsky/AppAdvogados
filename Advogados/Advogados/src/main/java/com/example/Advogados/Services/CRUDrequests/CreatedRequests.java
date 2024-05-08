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
import com.example.Advogados.message.message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CreatedRequests implements SavesRequests {
    
    
    private repositoryUser actionUser;
    private repositoryLawyers actionLawyer;
    private repositoryRequests action;
    private repositoryRelationShip actionRelation;
    private message msg;

    @Autowired
    public void setWired(repositoryUser actionUser, repositoryLawyers actionLawyers, repositoryRequests action, repositoryRelationShip actionRelation, message msg){
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

        List<Requests> existingRequestsUser = action.findRequestsByUserId(requests.getUser().getId());

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
                    if (i != requests.getLawyer().getId()) {

                        continue;

                    } else if (!lawyerStatus.asText().equalsIgnoreCase("pendente")) {

                        msg.setMensagem("Essa solicitacao foi: " + lawyerStatus.asText());
                        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
                    } else {

                        LawyerClientRelationship relationShip = new LawyerClientRelationship();

                        relationShip.setLawyer(requests.getLawyer());
                        relationShip.setClient(requests.getUser());
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
