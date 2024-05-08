package com.example.Advogados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Services.CRUDrelations.ReadRelations;
import com.example.Advogados.Services.CRUDrelations.saveRelation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/relationShip")
public class controllerRelationShip {

   
    private repositoryRelationShip action;
    private saveRelation service;
    private ReadRelations readRelations;

    @Autowired
    public void setWired(repositoryRelationShip action, ReadRelations relationReadLawyer, saveRelation service){
        this.action = action;
        this.readRelations = relationReadLawyer;
        this.service = service;

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
}
