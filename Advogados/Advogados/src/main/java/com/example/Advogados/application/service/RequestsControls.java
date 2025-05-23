package com.example.Advogados.application.service;

import com.example.Advogados.domains.dto.RequestDTO;
import com.example.Advogados.domains.response.RequestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RequestsControls {

    public List<RequestResponse> getRequests(final long id);
    public ResponseEntity<?> saveRequests(final RequestDTO request);
    public ResponseEntity<?> saveSecondRequests(final RequestDTO request);
    public ResponseEntity<HttpStatus> drop(final List<Long> id);

}
