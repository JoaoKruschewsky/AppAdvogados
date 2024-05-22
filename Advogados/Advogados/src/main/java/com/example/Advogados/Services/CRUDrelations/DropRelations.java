package com.example.Advogados.Services.CRUDrelations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.Services.interfaces.DropRelationsAndRequests.DropUI;
import com.example.Advogados.message.Message;

@Service
public class DropRelations implements DropUI {

    private RepositoryRelationShip action;
    private Message msg;

    @Autowired
    public void setWired(RepositoryRelationShip action, Message msg) {
        this.action = action;
        this.msg = msg;

    }

    @Override
    public ResponseEntity<?> drop(final List<Long> id) {

        action.deleteAllById(id);

        for (Long verifyID : id) {

            Optional<LawyerClientRelationship> verifyRelations = action.findById(verifyID);

            if (verifyRelations.isEmpty()) {
                msg.setMensagem("Relaceos apagada com sucesso.");
                return new ResponseEntity<>(msg, HttpStatus.OK);
            }
        }
        msg.setMensagem("Ocorreu um tente novamente mais tarde.");
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

}
