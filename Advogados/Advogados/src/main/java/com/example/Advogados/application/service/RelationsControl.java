package com.example.Advogados.application.service;

import com.example.Advogados.domains.dto.RelationShipDTO;
import com.example.Advogados.domains.dto.RelationShipUpdateDTO;
import com.example.Advogados.domains.response.RelationShipResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public interface RelationsControl {

    public List<RelationShipResponse> getRelations(final long id);
    ResponseEntity<?> saveRelation(final RelationShipDTO relation, JwtAuthenticationToken token);
    ResponseEntity<?> updateRelation(final RelationShipUpdateDTO relation);
    public ResponseEntity<HttpStatus> drop(final List<Long> id);



}
