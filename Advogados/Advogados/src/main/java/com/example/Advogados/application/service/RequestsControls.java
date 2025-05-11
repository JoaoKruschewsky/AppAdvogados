package com.example.Advogados.application.service;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.domains.response.RequestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RequestsControls {

    public List<RequestResponse> getRequests(final long id);
    public ResponseEntity<?> saveRequests(final Requests request);
    public ResponseEntity<?> saveSecondRequests(final Requests request);
    public ResponseEntity<HttpStatus> drop(final List<Long> id);

}
