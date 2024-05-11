package com.example.Advogados.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.Repository.repositoryRequests;
import com.example.Advogados.Services.CRUDrelations.ReadRelations;
import com.example.Advogados.Services.CRUDrequests.CreatedRequests;
import com.example.Advogados.Services.CRUDrequests.DropRequests;
import com.example.Advogados.Services.CRUDrequests.ReadRequests;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/requests")
public class controllerAllRequests {

    private CreatedRequests created;
    private ReadRequests read;
    private DropRequests drop;

    @Autowired
    public void setWired(CreatedRequests service, ReadRequests read, DropRequests drop) {
        this.created = service;
        this.read = read;
        this.drop = drop;
    }

    @PostMapping("firstRequest")
    public ResponseEntity<?> firstRequestSave(@RequestBody Requests request) {
        return created.saveRequests(request);
    }

    @PostMapping("secondRequests")
    public ResponseEntity<?> secondRequestSave(@RequestBody Requests request) {
        return created.saveSecondRequests(request);
    }

    @GetMapping("getRequestsUser/{id}")
    public ResponseEntity<?> getRequestsUser(@PathVariable Long id) {
        return read.ReadUser(id);
    }

    @DeleteMapping("dropRequests")
    public ResponseEntity<?> dropRequests(@RequestBody List<Long> ids) {
        return drop.drop(ids);
    }

}
