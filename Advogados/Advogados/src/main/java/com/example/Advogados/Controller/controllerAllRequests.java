package com.example.Advogados.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.Repository.RepositoryRequests;
import com.example.Advogados.Services.CRUDrelations.ReadRelations;
import com.example.Advogados.Services.CRUDrequests.CreatedRequests;
import com.example.Advogados.Services.CRUDrequests.DropRequests;
import com.example.Advogados.Services.CRUDrequests.ReadRequests;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/requests", produces = { "application/json" })
@Tag(name = "Request Controller for API JuríConecta")
public class ControllerAllRequests {

    private CreatedRequests created;
    private ReadRequests read;
    private DropRequests drop;

    @Autowired
    public void setWired(CreatedRequests service, ReadRequests read, DropRequests drop) {
        this.created = service;
        this.read = read;
        this.drop = drop;
    }

    @Operation(summary = "\r\n" + //
            "first request to change relationshi ", method = "POST")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "\r\n" + //
            "Request sent by the lawyer only the lawyer makes the first request", content = @Content(examples = @ExampleObject(" { \"status\": \"pending\",\"changeRelation\": \"In Progress\",\"lawyer\": {\"id\":1 },\"client\":{ \"id\":1} }"))),
            @ApiResponse(responseCode = "401", description = "\t\r\n" + //
                    "Unauthorized error with access token only Lawyers and not Users") })

    @PostMapping(path = "firstRequest", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_LAWYER')")
    public ResponseEntity<?> firstRequestSave(@RequestBody Requests request) {
        return created.saveRequests(request);
    }

    @Operation(summary = "\r\n" + //
            "\r\n" + //
            "second request after the first is sent to change relationshi ", method = "POST")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "\r\n" + //
            "Request sent by the user only the user makes the second request", content = @Content(examples = @ExampleObject(" { \"status\": \"confirmed\",\"changeRelation\": \"In Progress\",\"lawyer\": {\"id\":1 },\"client\":{ \"id\":1} }"))),
            @ApiResponse(responseCode = "401", description = "\t\r\n" + //
                    "Unauthorized error with access token only Users and not Lawyers") })
    @PostMapping(path = "secondRequests", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> secondRequestSave(@RequestBody Requests request) {
        return created.saveSecondRequests(request);
    }

    @Operation(summary = "\r\n" + //
                "catch requests made to the user")
    @GetMapping("getRequestsUser/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> getRequestsUser(@PathVariable Long id) {
        return read.readUser(id);
    }


    @Operation( summary = "deletes requests that the user selected")
    @DeleteMapping("dropRequests")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> dropRequests(@RequestBody List<Long> ids) {
        return drop.drop(ids);
    }

}
