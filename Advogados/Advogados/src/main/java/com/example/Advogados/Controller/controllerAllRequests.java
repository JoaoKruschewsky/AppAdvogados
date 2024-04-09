package com.example.Advogados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.Repository.repositoryAllRequests;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/requests")
public class controllerAllRequests {

    @Autowired
    repositoryAllRequests action;

    @PostMapping("firstRequest")
    public Requests firstRequestSave(@RequestBody Requests request) {
        return action.save(request);
    }

    @PostMapping("secondRequest")
    public Requests secondRequestSave(@RequestBody Requests request) {
        return action.save(request);
    }
}
