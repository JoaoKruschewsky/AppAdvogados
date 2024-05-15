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
import com.example.Advogados.Model.DTO.Lawyer.LoginLawyerDTO;
import com.example.Advogados.Model.DTO.Lawyer.updateLawyerDTO;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Services.CRUDlawyer.loginLawyer;
import com.example.Advogados.Services.CRUDlawyer.saveLawyer;
import com.example.Advogados.Services.CRUDlawyer.updateLawyerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/lawyer")
public class ControllerLawyers {

    private repositoryLawyers action;
    private saveLawyer saveService;
    private loginLawyer loginService;
    private updateLawyerService updateLawyerService;

    @Autowired
    public void setWired(repositoryLawyers action, saveLawyer saveService, loginLawyer loginService,
            updateLawyerService updateLawyerService) {
        this.action = action;
        this.saveService = saveService;
        this.loginService = loginService;
        this.updateLawyerService = updateLawyerService;
    }

    @PostMapping("saveLawyer")
    public ResponseEntity<?> saveLawyers(@RequestBody Lawyers lawyers) {
        return saveService.verifySaveLawyer(lawyers);
    }

    @PostMapping("verifyLawyer")
    public ResponseEntity<?> verifyLawyers(@RequestBody LoginLawyerDTO Lawyers) {
        return loginService.verifyLoginLawyers(Lawyers);
    }

    @GetMapping("getLawyer")
    public List<Lawyers> getLawyers() {
        return action.findAll();
    }

    @PostMapping("saveUpdatesLawyer/{id}")
    public ResponseEntity<?> saveimg(@PathVariable Long id, @RequestBody updateLawyerDTO updateDTO) {
        return updateLawyerService.updateLawyer(id, updateDTO);
    }

    @GetMapping("getLawyerbyName/{name}")
    public List<Lawyers> getLawyerEmail(@PathVariable String name) {
        return action.findBynameStartingWithIgnoreCase(name);
    }

}
