package com.example.Advogados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/lawyer", produces = { "application/json" })
@Tag(name = "Register Lawyers")
public class ControllerLawyers {

    private RepositoryLawyers action;
    private SaveLawyer saveService;
    private UpdateLawyerService updateLawyerService;

    @Autowired
    public void setWired(RepositoryLawyers action, SaveLawyer saveService,
            UpdateLawyerService updateLawyerService) {
        this.action = action;
        this.saveService = saveService;
        this.updateLawyerService = updateLawyerService;
    }

    @Operation(summary = "Register Lawyers", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lawyer eligible for registration"),
            @ApiResponse(responseCode = "400", description = "There is already a lawyer with an email or CPF"),
            @ApiResponse(responseCode = "401", description = "You need a real lawyer to pass spring security authentication")
    })
    @PostMapping(path = "saveLawyer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLawyers(@Valid @RequestBody Lawyers lawyers) {
        return saveService.verifySaveLawyer(lawyers);
    }

    @Operation(summary = "\r\n" + //
            "Get all lawyers and show the user that you are looking for lawyers", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Show every lawyers"),
            @ApiResponse(responseCode = "400", description = "No register lawyers")
    })
    @GetMapping("getLawyer")
    public List<Lawyers> getLawyers() {
        return action.findAll();
    }

    @Operation(summary = "\r\n" + //
            "saves lawyer updates when he updates his profile", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save updates"),
            @ApiResponse(responseCode = "401", description = "\r\n" + //
                    "Unauthorized error with access token")
    })
    @PostMapping(path = "saveUpdatesLawyer/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_LAWYER')")
    public ResponseEntity<?> saveimg(@PathVariable Long id, @RequestBody UpdateLawyerDTO updateDTO,
            JwtAuthenticationToken token) {
        return updateLawyerService.updateLawyer(id, updateDTO, token);
    }

    @Operation(summary = "Searching for lawyer name")
    @ApiResponses(value = {
            @ApiResponse(description = "\r\n" + //
                    "No code response, as it was made to place a search bar.\r\n" + //
                    "If you don't find anything, the bar won't load, etc...")
    })
    @GetMapping("getLawyerbyName/{name}")
    public List<Lawyers> getLawyerEmail(@PathVariable String name) {
        return action.findBynameStartingWithIgnoreCase(name);
    }

}
