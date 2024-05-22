package com.example.Advogados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.DTO.Lawyer.LoginLawyerDTO;
import com.example.Advogados.Model.DTO.Lawyer.UpdateLawyerDTO;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Services.CRUDlawyer.LoginLawyer;
import com.example.Advogados.Services.CRUDlawyer.SaveLawyer;
import com.example.Advogados.Services.CRUDlawyer.UpdateLawyerService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/lawyer")
public class ControllerLawyers {

    private RepositoryLawyers action;
    private SaveLawyer saveService;
    private LoginLawyer loginService;
    private UpdateLawyerService updateLawyerService;

    @Autowired
    public void setWired(RepositoryLawyers action, SaveLawyer saveService, LoginLawyer loginService,
            UpdateLawyerService updateLawyerService) {
        this.action = action;
        this.saveService = saveService;
        this.loginService = loginService;
        this.updateLawyerService = updateLawyerService;
    }

    @PostMapping("saveLawyer")
    public ResponseEntity<?> saveLawyers(@Valid @RequestBody Lawyers lawyers) {
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
    @PreAuthorize("hasAuthority('SCOPE_LAWYER')")
    public ResponseEntity<?> saveimg(@PathVariable Long id, @RequestBody UpdateLawyerDTO updateDTO,
            JwtAuthenticationToken token) {
        return updateLawyerService.updateLawyer(id, updateDTO, token);
    }

    @GetMapping("getLawyerbyName/{name}")
    public List<Lawyers> getLawyerEmail(@PathVariable String name) {
        return action.findBynameStartingWithIgnoreCase(name);
    }

}
