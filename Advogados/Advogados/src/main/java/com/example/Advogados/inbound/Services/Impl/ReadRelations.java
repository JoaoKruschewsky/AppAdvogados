package com.example.Advogados.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.Advogados.Services.GetRelations;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.message.Message;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ReadRelations implements GetRelations {

    private final RepositoryRelationShip action;
    private final Message msg;

    @Override
    public List<Object> ReadLawyer(final Long id) {
        List<LawyerClientRelationship> Lawyer = action.findAllLawyerClientRelationshipsByLawyerId(id);
        if (!Lawyer.isEmpty()) {
            return  Lawyer.stream().flatMap(idRelation -> Stream.of(
                            idRelation.getLawyer().getId(),
                            idRelation.getClient().getId(),
                            idRelation.getClient().getName(),
                            idRelation.getStatus(),
                            idRelation.getDateCreateRelation()
                    )).collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Object> ReadUser(final Long id) {
        List<LawyerClientRelationship> user = action.findAllLawyerClientRelationshipsByClientId(id);
        if (!user.isEmpty()) {
            return user.stream().flatMap( users -> Stream.of(
                    users.getClient().getId(),
                    users.getLawyer().getId(),
                    users.getLawyer().getName(),
                    users.getStatus(),
                    users.getDateCreateRelation()))
                    .collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
