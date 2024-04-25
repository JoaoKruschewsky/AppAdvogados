package com.example.Advogados.Services.interfaces.email;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

public interface emailInterface {
    
    public ResponseEntity<?> emailService(String email);
}
