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
import com.example.Advogados.Services.CRUDrelations.relationReadLawyer;
import com.example.Advogados.Services.CRUDrelations.relationReadUser;
import com.example.Advogados.Services.CRUDrelations.saveRelation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/relationShip")
public class controllerRelationShip {

   
    private repositoryRelationShip action;
    private saveRelation service;
    private relationReadLawyer relationReadLawyer;
    private relationReadUser relationReadUser;

    @Autowired
    public void setWired(repositoryRelationShip action, relationReadLawyer relationReadLawyer, saveRelation service, relationReadUser relationReadUser){
        this.action = action;
        this.relationReadLawyer = relationReadLawyer;
        this.service = service;
        this.relationReadUser = relationReadUser;

    }

    @PostMapping("saveRelation")
    public ResponseEntity<?> saveRelation(@RequestBody LawyerClientRelationship relationship) {
        return service.saveRelation(relationship);
    }

    @GetMapping("getRelationUser/{id}")
    public ResponseEntity<?> getRelationUser(@PathVariable Long id) {
        return relationReadUser.getRelationShip(id);
    }

    @GetMapping("getRelationLawyer/{id}")
    public ResponseEntity<?> getRelationLawyer(@PathVariable Long id) {
        return relationReadLawyer.getRelationShip(id);
    }
}
