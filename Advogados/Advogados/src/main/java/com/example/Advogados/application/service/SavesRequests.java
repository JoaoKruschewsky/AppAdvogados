package com.example.Advogados.application.service;

import com.example.Advogados.Model.Requests;
import org.springframework.http.ResponseEntity;

public interface SavesRequests {
    
    public ResponseEntity<?> saveRequests(final Requests request);

    public ResponseEntity<?> saveSecondRequests(final Requests request);
}
