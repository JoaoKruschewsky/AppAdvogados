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
import com.example.Advogados.Services.loginService.loginLawyer;
import com.example.Advogados.Services.registerService.saveLawyer;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/lawyer")
public class controllerLawyers {

    
    private serviceLawyers service;
    private repositoryLawyers action;
    private saveLawyer saveService;
    private loginLawyer loginService;

    @Autowired
    public void setWired(serviceLawyers service, repositoryLawyers action,saveLawyer saveService, loginLawyer loginService){
        this.service = service;
        this.action = action;
        this.saveService = saveService;
        this.loginService = loginService;
    }

    @PostMapping("saveLawyer")
    public ResponseEntity<?> saveLawyers(@RequestBody Lawyers lawyers) {
        return saveService.verifySaveLawyer(lawyers);
    }

    @PostMapping("verifyLawyer")
    public ResponseEntity<?> verifyLawyers(@RequestBody Lawyers Lawyers) {
        return loginService.verifySaveLawyers(Lawyers);
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
