package com.example.Advogados.adapters.inbound;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Services.Impl.DropService;
import com.example.Advogados.Services.Impl.ReadRelations;
import com.example.Advogados.Services.Impl.SaveRelation;
import com.example.Advogados.application.service.RelationsControl;
import com.example.Advogados.domains.dto.RelationShipDTO;
import com.example.Advogados.domains.dto.RelationShipUpdateDTO;
import com.example.Advogados.domains.response.RelationShipResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/relationShip")
@Tag(name = "Controller RelationShip for API JuríConecta")
public class ControllerRelationShip {


    private final RelationsControl relationsControl;

    public ControllerRelationShip(RelationsControl relationsControl) {
        this.relationsControl = relationsControl;
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
    public ResponseEntity<?> saveRelation(@RequestBody RelationShipDTO relationship,
            JwtAuthenticationToken token) {
        return relationsControl.saveRelation(relationship, token);
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
    @ResponseStatus(HttpStatus.OK)
    public List<RelationShipResponse> getRelationUser(@PathVariable Long id) {
        return relationsControl.getRelations(id);
    }

    @Operation(summary = "deletes requests that the user or Lawyer selected")
    @DeleteMapping(path = "dropService", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteRelations(@RequestBody List<Long> id) {
        return relationsControl.drop(id);
    }

    @PostMapping(path = "saveUpdateRelation")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> saveUpdate(@RequestBody RelationShipUpdateDTO relation) {
        return relationsControl.updateRelation(relation);
    }
}
