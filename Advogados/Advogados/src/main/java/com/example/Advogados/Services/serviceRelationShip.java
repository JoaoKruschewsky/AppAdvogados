package com.example.Advogados.Services;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Repository.repositoryUser;

@Service
public class serviceRelationShip {

    private repositoryRelationShip action;

    private repositoryUser actionUser;

    public ResponseEntity<?> getRelations(long id) {
        Optional<User> user = actionUser.findById(id);

        if (user != null) {
            
            return new ResponseEntity<>()
        }

    }
}
