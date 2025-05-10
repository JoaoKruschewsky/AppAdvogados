package com.example.Advogados.application.service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.domains.response.RelationShipResponse;
import com.example.Advogados.domains.response.RelationShipUsersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public interface RelationsControl {

    public List<RelationShipResponse> getRelations(final long id);
    ResponseEntity<?> saveRelation(final LawyerClientRelationship relation, JwtAuthenticationToken token);
    ResponseEntity<?> updateRelation(final LawyerClientRelationship relation);


}
