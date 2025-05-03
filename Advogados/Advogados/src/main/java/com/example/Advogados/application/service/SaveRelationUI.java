package com.example.Advogados.application.service;

import com.example.Advogados.Model.LawyerClientRelationship;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface SaveRelationUI {

    ResponseEntity<?> saveNewRelation(final LawyerClientRelationship relation, JwtAuthenticationToken token);

    ResponseEntity<?> saveUpdateRelation(final LawyerClientRelationship relation);

}