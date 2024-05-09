package com.example.Advogados.Services.interfaces.DropRelationsAndRequests;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

public interface DropUI {

    public ResponseEntity<?> drop(final ArrayList<Long> id);
}
