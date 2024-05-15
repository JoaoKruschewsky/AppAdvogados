package com.example.Advogados.Services.interfaces.relations;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.LawyerClientRelationship;

public interface saveRelationUI {

    ResponseEntity<?> saveNewRelation(LawyerClientRelationship relation);

}