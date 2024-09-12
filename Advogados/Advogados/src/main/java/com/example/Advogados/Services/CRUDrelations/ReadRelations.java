package com.example.Advogados.Services.CRUDrelations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Enum.RelationEnum;
import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.Services.interfaces.relations.GetRelations;
import com.example.Advogados.message.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.swing.*;

@Service
@AllArgsConstructor
public class ReadRelations implements GetRelations {

    private final RepositoryRelationShip action;
    private final Message msg;

    @Override
    public ResponseEntity<?> ReadLawyer(final Long id) {
        List<LawyerClientRelationship> Lawyer = action.findAllLawyerClientRelationshipsByLawyerId(id);
        if (!Lawyer.isEmpty()) {

            ArrayList<Object> namesAdd = new ArrayList<>();

            Lawyer.forEach(idRelation -> namesAdd.addAll(List.of(idRelation.getLawyer().getId(),
                    idRelation.getClient().getId(),
                    idRelation.getClient().getName(),
                    idRelation.getStatus(),
                    idRelation.getDateCreateRelation()
            )));
            return ResponseEntity.ok().body(namesAdd);
        }

        return ResponseEntity.ok().body("Nao tem Relacoes");
    }

    @Override
    public ResponseEntity<?> ReadUser(final Long id) {
        List<LawyerClientRelationship> user = action.findAllLawyerClientRelationshipsByClientId(id);
        if (!user.isEmpty()) {
            ArrayList<Object> names = new ArrayList<>();
            user.forEach(idRelation -> names.addAll(List.of(idRelation.getClient().getId(),
                    idRelation.getLawyer().getId(),
                    idRelation.getLawyer().getName(),
                    idRelation.getStatus(),
                    idRelation.getDateCreateRelation()
            )));
            return ResponseEntity.ok().body(names);
        }
        return ResponseEntity.ok().body("Nao tem Relacoes");
    }
}
