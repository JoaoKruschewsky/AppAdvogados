package com.example.Advogados.Controller;

import java.util.List;

import com.example.Advogados.Services.CRUDrelations.DropService;
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
import com.example.Advogados.Services.CRUDrequests.CreatedRequests;
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
    private DropService drop;

    @Autowired
    public void setWired(CreatedRequests service, ReadRequests read, DropService drop) {
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return all request by id in a list", content = @Content(examples = @ExampleObject("[\"id by user\", \"name lawyer\", \"status da request\", \"name change relation\", \"id by lawyer\", \"2024-05-30\"]"))),
            @ApiResponse(responseCode = "400", description = "User have not relation"),
            @ApiResponse(responseCode = "500", description = "I have a try catch reading a json if it gives an error it returns 500"),
            @ApiResponse(responseCode = "401", description = "\r\n" + //
                    "Unauthorized error with access token")

    })
    @GetMapping("getRequestsUser/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> getRequestsUser(@PathVariable Long id) {
        return read.readUser(id);
    }

    @Operation(summary = "deletes requests that the user selected")
    @DeleteMapping("dropRequests")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> dropRequests(@RequestBody List<Long> ids) {
        return drop.dropAllById(ids);
    }

}
