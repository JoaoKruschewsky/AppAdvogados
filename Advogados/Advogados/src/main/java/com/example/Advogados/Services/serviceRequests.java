package com.example.Advogados.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryRequests;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.message.message;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class serviceRequests {
    @Autowired
    private repositoryUser actionUser;

    @Autowired
    private repositoryLawyers actionLawyer;

    @Autowired
    private repositoryRequests action;

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
            ArrayList<String> names = new ArrayList<>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(user);
                JsonNode rootNode = objectMapper.readTree(json);

                for (JsonNode node : rootNode) {

                    JsonNode lawyerNode = node.get("lawyers");
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

    public ResponseEntity<?> saveSecondRequests(Requests requests) {

        List<Requests> existingRequestsUser = action.findRequestsByUserId(requests.getUsers().getId());

        if (!existingRequestsUser.isEmpty()) {

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(existingRequestsUser);
                JsonNode rootNode = objectMapper.readTree(json);

                for (JsonNode node : rootNode) {

                    JsonNode lawyerNode = node.get("lawyers");
                    JsonNode lawyerStatus = node.get("status");
                    int i = lawyerNode.get("id").asInt();

                    if (i == requests.getLawyers().getId()) {
                        if (lawyerStatus.asText().equals("pendente")) {
                            action.save(requests);
                            msg.setMensagem("Solicitacao salva com sucesso");
                            return new ResponseEntity<>(msg, HttpStatus.OK);
                        } else {
                            msg.setMensagem("Essa solicitacao já foi: " + lawyerStatus.asText());
                            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<>("Nao tem uma solicitacao com esse Advogado", HttpStatus.BAD_REQUEST);

                    }
                }

                return new ResponseEntity<>(null);

            } catch (JsonProcessingException e) {

                e.printStackTrace();
                msg.setMensagem("User não encontrado");
                return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            msg.setMensagem("Não foi possível encontrar o Usuario ");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
