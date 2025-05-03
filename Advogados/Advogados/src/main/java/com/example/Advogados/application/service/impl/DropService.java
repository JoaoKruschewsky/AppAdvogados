package com.example.Advogados.application.service.impl;

import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.Services.DropUI;
import com.example.Advogados.message.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DropService implements DropUI {

    private final RepositoryRelationShip action;
    private final Message msg;

    public ResponseEntity<HttpStatusCode> dropAllById(final List<Long> idRelation) {

        action.deleteAllById(idRelation);

        idRelation.forEach(id -> {
            boolean exist = action.findById(id).isEmpty();

            if(exist) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu um erro");
            }
        });

        return ResponseEntity.ok(HttpStatusCode.valueOf(204));
    }

}
