package com.example.Advogados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Services.CRUDrelations.DropRElations;
import com.example.Advogados.Services.CRUDrelations.DropRelations;
import com.example.Advogados.Services.CRUDrelations.ReadRelations;
import com.example.Advogados.Services.CRUDrelations.saveRelation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/relationShip")
public class controllerRelationShip {

    private saveRelation service;
    private ReadRelations readRelations;
    private DropRelations dropRelations;

    @Autowired
    public void setWired(ReadRelations relationReadLawyer, saveRelation service,
            DropRelations dropRelations) {
        this.readRelations = relationReadLawyer;
        this.service = service;
        this.dropRelations = dropRelations;

    }

    @PostMapping("saveRelation")
    public ResponseEntity<?> saveRelation(@RequestBody LawyerClientRelationship relationship) {
        return service.saveRelation(relationship);
    }

    @GetMapping("getRelationUser/{id}")
    public ResponseEntity<?> getRelationUser(@PathVariable Long id) {
        return readRelations.ReadUser(id);
    }

    @GetMapping("getRelationLawyer/{id}")
    public ResponseEntity<?> getRelationLawyer(@PathVariable Long id) {
        return readRelations.ReadLawyer(id);
    }

    @DeleteMapping("dropRelations")
    public ResponseEntity<?> deleteRelations(@RequestBody List<Long> id) {
        return dropRelations.drop(id);
    }
}
