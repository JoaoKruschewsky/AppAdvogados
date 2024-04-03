package com.example.Advogados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/relationShip")
public class controllerRelationShip {

    @Autowired
    private repositoryRelationShip action;

    @PostMapping("saveRelation")
    public LawyerClientRelationship saveRelation(@RequestBody LawyerClientRelationship relationship) {
        return action.save(relationship);
    }

    @GetMapping("getRelation/{id}")
    public LawyerClientRelationship getRelationByID(@PathVariable User id) {
        return action.findByClient(id);
    }
}
