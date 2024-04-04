package com.example.Advogados.Services;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.message.message;

@Service
public class serviceRelationShip {

    @Autowired
    private repositoryRelationShip action;

    @Autowired
    private message msg;

    public ResponseEntity<?> getRelations(long id) {
        Optional<String> user = action.findClientById(id);

        if (user.isPresent()) {

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            msg.setMensagem("Não existe usuario com esse nome");
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }

    }
}
