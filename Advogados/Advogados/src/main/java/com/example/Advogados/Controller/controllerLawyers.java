package com.example.Advogados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Model.updateDTO;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Services.serviceLawyers;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/lawyer")
public class controllerLawyers {

    @Autowired
    private serviceLawyers service;

    @Autowired
    private repositoryLawyers action;

    @PostMapping("saveLawyer")
    public ResponseEntity<?> saveLawyers(@RequestBody Lawyers lawyers, BindingResult result) {
        return service.saveLawyers(lawyers, result);
    }

    @PostMapping("verifyLawyer")
    public ResponseEntity<?> verifyLawyers(@RequestBody Lawyers Lawyers) {
        return service.verifyLawyers(Lawyers);
    }

    @GetMapping("getLawyer")
    public List<Lawyers> getLawyers() {
        return action.findAll();
    }

    @PostMapping("saveUpdatesLawyer/{id}")
    public ResponseEntity<?> saveimg(@PathVariable Long id, @RequestBody updateDTO updateDTO) {
        return service.uptade(id, updateDTO);
    }

    @GetMapping("getLawyerbyName/{name}")
    public List<Lawyers> getLawyerEmail(@PathVariable String name) {
        return action.findBynameStartingWithIgnoreCase(name);
    }

}
