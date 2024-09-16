package com.example.Advogados.Services.CRUDrequests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.Requests;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.Repository.RepositoryRequests;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.Services.interfaces.requests.SavesRequests;
import com.example.Advogados.message.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CreatedRequests implements SavesRequests {

    private final RepositoryUser actionUser;
    private final RepositoryLawyers actionLawyer;
    private final RepositoryRequests action;

    @Override
    public ResponseEntity<HttpStatus> saveRequests(final Requests requests) {

        Optional<User> existingUser = actionUser.findById(requests.getUser().getId());
        Optional<Lawyers> existingLawyer = actionLawyer.findById(requests.getLawyer().getId());

        if (existingUser.isPresent() && existingLawyer.isPresent()) {
            action.save(requests);

            return ResponseEntity.ok().build();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpStatus> saveSecondRequests(final Requests requests) {

        Optional<User> existingUser = actionUser.findById(requests.getUser().getId());
        Optional<Lawyers> existingLawyer = actionLawyer.findById(requests.getLawyer().getId());

        if (existingUser.isPresent() && existingLawyer.isPresent()) {
            action.save(requests);

            return ResponseEntity.ok().build();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
