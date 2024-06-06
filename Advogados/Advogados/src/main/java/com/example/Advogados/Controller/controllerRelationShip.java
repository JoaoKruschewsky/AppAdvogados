package com.example.Advogados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.Services.CRUDrelations.DropRelations;
import com.example.Advogados.Services.CRUDrelations.ReadRelations;
import com.example.Advogados.Services.CRUDrelations.SaveRelation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/relationShip")
@Tag(name = "Controller RelationShip for API JuríConecta")
public class ControllerRelationShip {

    private SaveRelation service;
    private ReadRelations readRelations;
    private DropRelations dropRelations;

    @Autowired
    public void setWired(ReadRelations relationReadLawyer, SaveRelation service,
            DropRelations dropRelations) {
        this.readRelations = relationReadLawyer;
        this.service = service;
        this.dropRelations = dropRelations;

    }

    @Operation(summary = "\r\n" + //
            "Relationship saved when user meets his lawyer and wants to talk to him")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "save New Relation", content = @Content(examples = @ExampleObject("{ \"status\": \"em andamento\", \"dateCreateRelation\": \"é salva automaticamente\", \"lawyer\": {\"id\": 5 }, \"client\": { \"id\": 5 } }"))),
            @ApiResponse(responseCode = "401", description = "\r\n" + //
                    "Unauthorized error with access token")
    })
    @PostMapping(path = "saveRelation", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> saveRelation(@RequestBody LawyerClientRelationship relationship,
            JwtAuthenticationToken token) {
        return service.saveNewRelation(relationship, token);
    }

    @Operation(summary = "\r\n" + //
            "get the user relationship by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return all relationships by id in a list", content = @Content(examples = @ExampleObject("[\"name lawyer\", \"status of relation\", \"2024-05-30\"]"))),
            @ApiResponse(responseCode = "400", description = "User have not relation"),
            @ApiResponse(responseCode = "500", description = "I have a try catch reading a json if it gives an error it returns 500"),
            @ApiResponse(responseCode = "401", description = "\r\n" + //
                    "Unauthorized error with access token")

    })
    @GetMapping("getRelationUser/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> getRelationUser(@PathVariable Long id) {
        return readRelations.ReadUser(id);
    }

    @Operation(summary = "\r\n" + //
            "get the Lawyer relationship by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return all relationships by id in a lit", content = @Content(examples = @ExampleObject("[\"name user\", \"status of relation\", \"2024-05-30\"]"))),
            @ApiResponse(responseCode = "400", description = "Lawyer have not relation"),
            @ApiResponse(responseCode = "500", description = "I have a try catch reading a json if it gives an error it returns 500"),
            @ApiResponse(responseCode = "401", description = "\r\n" + //
                    "Unauthorized error with access token")

    })
    @GetMapping("getRelationLawyer/{id}")
    @PreAuthorize("hasAuthority('SCOPE_LAWYER')")
    public ResponseEntity<?> getRelationLawyer(@PathVariable Long id) {
        return readRelations.ReadLawyer(id);
    }

    @Operation(summary = "deletes requests that the user or Lawyer selected")
    @DeleteMapping(path = "dropRelations", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRelations(@RequestBody List<Long> id) {
        return dropRelations.drop(id);
    }

    @PostMapping(path = "saveUpdateRelation")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> saveUpdate(@RequestBody LawyerClientRelationship relation) {
        return service.saveUpdateRelation(relation);
    }
}
