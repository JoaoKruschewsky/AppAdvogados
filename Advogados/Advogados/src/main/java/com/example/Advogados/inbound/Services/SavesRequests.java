package com.example.Advogados.Services;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.Requests;

public interface SavesRequests {
    
    public ResponseEntity<?> saveRequests(final Requests request);

    public ResponseEntity<?> saveSecondRequests(final Requests request);
}
