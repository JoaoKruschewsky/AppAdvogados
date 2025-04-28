package com.example.Advogados.Services.Impl;

import java.util.Optional;

import com.example.Advogados.Services.SavesRequests;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.Requests;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Repository.RepositoryRequests;
import com.example.Advogados.Repository.RepositoryUser;
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
