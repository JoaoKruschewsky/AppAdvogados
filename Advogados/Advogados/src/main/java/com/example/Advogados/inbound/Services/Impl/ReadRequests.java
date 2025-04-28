package com.example.Advogados.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.Advogados.Services.GetRequests;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.Repository.RepositoryRequests;
import com.example.Advogados.message.Message;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReadRequests implements GetRequests {
    private RepositoryRequests action;
    private Message msg;

    @Autowired
    public void setWired(RepositoryRequests action, Message msg) {
        this.action = action;
        this.msg = msg;

    }

    @Override
    public List<Object> readUser(final long id) {
        List<Requests> user = action.findRequestsByUserId(id);
        if (!user.isEmpty()) {
            return user.stream().flatMap(idRequest -> Streams.of(
                    idRequest.getUser().getId(),
                    idRequest.getUser().getName(),
                    idRequest.getLawyer().getId(),
                    idRequest.getLawyer().getName(),
                    idRequest.getDateCreateRequests(),
                    idRequest.getChangeRelation()
            )).collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe nenhuma solicitação pra esse usuario");
    }
}
