package com.example.Advogados.application.service.impl;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.application.service.RequestsControls;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RequestsControlsImpl implements RequestsControls {
    @Override
    public List<Object> getRequests(long id) {
        return List.of();
    }

    @Override
    public ResponseEntity<?> saveSecondRequests(Requests request) {
        return null;
    }

    @Override
    public ResponseEntity<?> saveRequests(Requests request) {
        return null;
    }
}
