package com.example.Advogados.application.usecase;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.exception.RelationsException;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ResponseStatusException;

public class RelationShipUseCase {

    public static void validRelationShip(LawyerClientRelationship body, JwtAuthenticationToken token) {

        if(!body.getClient().getId().equals(Long.parseLong(token.getName()))) {
            throw new RelationsException("Lawyers cannot make relationships", HttpStatusCode.valueOf(401));
        }
    }
}
