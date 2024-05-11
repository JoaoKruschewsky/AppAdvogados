package com.example.Advogados.Services.interfaces.DropRelationsAndRequests;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface DropUI {

    public ResponseEntity<?> drop(final List<Long> id);
}
