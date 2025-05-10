package com.example.Advogados.application.service;

import com.example.Advogados.Model.Requests;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RequestsControls {

    public List<Object> getRequests(final long id);
    public ResponseEntity<?> saveRequests(final Requests request);
    public ResponseEntity<?> saveSecondRequests(final Requests request);

}
