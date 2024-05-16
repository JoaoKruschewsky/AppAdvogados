package com.example.Advogados.Services.CRUDrequests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.Repository.repositoryRequests;
import com.example.Advogados.Services.interfaces.DropRelationsAndRequests.DropUI;
import com.example.Advogados.message.Message;

@Service
public class DropRequests implements DropUI {

    private repositoryRequests action;
    private Message msg;

    @Autowired
    public void setWired(repositoryRequests action, Message msg) {
        this.action = action;
        this.msg = msg;

    }

    @Override
    public ResponseEntity<?> drop(final List<Long> id) {
        action.deleteAllById(id);

        for (Long verifyID : id) {

            Optional<Requests> verifyRequests = action.findById(verifyID);

            if (verifyRequests.isEmpty()) {
                msg.setMensagem("Solicitacoes apagada com sucesso.");
                return new ResponseEntity<>(msg, HttpStatus.OK);
            }
        }
        msg.setMensagem("Ocorreu um tente novamente mais tarde.");
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}
