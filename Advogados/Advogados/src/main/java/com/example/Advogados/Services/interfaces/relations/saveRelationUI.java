package com.example.Advogados.Services.interfaces.relations;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.example.Advogados.Model.LawyerClientRelationship;

public interface SaveRelationUI {

    ResponseEntity<?> saveNewRelation(LawyerClientRelationship relation, JwtAuthenticationToken token);

}