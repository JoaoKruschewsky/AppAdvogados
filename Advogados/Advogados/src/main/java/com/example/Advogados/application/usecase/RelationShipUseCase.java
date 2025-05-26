package com.example.Advogados.application.usecase;

import com.example.Advogados.application.exception.RelationsException;
import com.example.Advogados.domains.LawyerClientRelationship;
import com.example.Advogados.domains.dto.RelationShipDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ResponseStatusException;

public class RelationShipUseCase {

    public static void validRelationShip(RelationShipDTO body, JwtAuthenticationToken token) {

        if(!body.idUser().equals(Long.parseLong(token.getName()))) {
            throw new RelationsException("Lawyers cannot make relationships", HttpStatusCode.valueOf(401));
        }
    }
}
