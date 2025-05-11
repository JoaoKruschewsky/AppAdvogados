package com.example.Advogados.application.service.impl;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.Requests;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.RepositoryRequests;
import com.example.Advogados.application.builder.RequestBuilder;
import com.example.Advogados.application.helper.RequestHelper;
import com.example.Advogados.application.service.RequestsControls;
import com.example.Advogados.domains.response.RequestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class RequestsControlsImpl implements RequestsControls {

    private final RepositoryRequests action;


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

    public RequestsControlsImpl(RepositoryRequests action) {
        this.action = action;
    }

    @Override
    public List<RequestResponse> getRequests(final long id) {

        List<Requests> requests = action.findRequestsByUserId(id);
        RequestHelper.ifContains(requests);

        return RequestBuilder.requestBuilder(requests);
    }

    @Override
    public ResponseEntity<?> saveSecondRequests( final Requests request) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> saveRequests(final Requests request) {

        action.save(request);

        return ResponseEntity.ok().build();
    }
}
