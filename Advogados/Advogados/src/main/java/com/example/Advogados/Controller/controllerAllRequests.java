package com.example.Advogados.Controller;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.Repository.repositoryRequests;
import com.example.Advogados.Services.serviceRequests;
import com.example.Advogados.Services.CRUDrelations.readLawyer;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/requests")
public class controllerAllRequests {

  
    private repositoryRequests action;
    private serviceRequests service;
    
    @Autowired
    public void setWired(repositoryRequests action, serviceRequests service){
        this.action = action;
        this.service = service;
    }

    @PostMapping("firstRequest")
    public ResponseEntity<?> firstRequestSave(@RequestBody Requests request) {
        return service.saveRequests(request);
    }

    @PostMapping("secondRequests")
    public ResponseEntity<?> secondRequestSave(@RequestBody Requests request) {
        return service.saveSecondRequests(request);
    }

    @GetMapping("getRequestsUser/{id}")
    public ResponseEntity<?> getRequestsUser(@PathVariable Long id) {
        return service.getRequestsUser(id);
    }
}
