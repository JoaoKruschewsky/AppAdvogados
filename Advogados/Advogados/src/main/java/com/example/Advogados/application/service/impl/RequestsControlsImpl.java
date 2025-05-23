package com.example.Advogados.application.service.impl;


import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Repository.RepositoryRequests;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.application.builder.RequestBuilder;
import com.example.Advogados.application.helper.RequestHelper;
import com.example.Advogados.application.service.RequestsControls;
import com.example.Advogados.domains.Lawyers;
import com.example.Advogados.domains.Requests;
import com.example.Advogados.domains.User;
import com.example.Advogados.domains.dto.RequestDTO;
import com.example.Advogados.domains.response.RequestResponse;
import com.example.Advogados.mapper.RequestMapper;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class RequestsControlsImpl implements RequestsControls {

    private final RepositoryRequests action;
    private final RequestMapper requestMapper;
    private final RepositoryLawyers repositoryLawyers;
    private final RepositoryUser repositoryUser;

    public RequestsControlsImpl(RepositoryRequests action, RequestMapper requestMapper, RepositoryLawyers repositoryLawyers, RepositoryUser repositoryUser) {
        this.action = action;
        this.requestMapper = requestMapper;
        this.repositoryLawyers = repositoryLawyers;
        this.repositoryUser = repositoryUser;
    }

    @Override
    public ResponseEntity<HttpStatus> drop(final List<Long> id) {
        action.deleteAllById(id);

        id.forEach(i -> {
            boolean exist = action.findById(i).isEmpty();

            if(exist) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu um erro");
            }
        });

        return ResponseEntity.noContent().build();
    }


    @Override
    public List<RequestResponse> getRequests(final long id) {

        List<Requests> requests = action.findRequestsByUserId(id);
        RequestHelper.ifContains(requests);

        return RequestBuilder.requestBuilder(requests);
    }

    @Override
    public ResponseEntity<?> saveSecondRequests( final RequestDTO request) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> saveRequests(final RequestDTO request) {
        Optional<Lawyers> getLawyer = repositoryLawyers.findById(request.idLawyer());
        Optional<User> getUser = repositoryUser.findById(request.idUser());

        Requests builderRequest = new Requests();
        builderRequest.setLawyer(getLawyer.get());
        builderRequest.setUser(getUser.get());
        builderRequest.setStatus(request.status());
        builderRequest.setChangeRelation(request.changerelation());

        action.save(builderRequest);


        return ResponseEntity.ok().build();
    }
}
