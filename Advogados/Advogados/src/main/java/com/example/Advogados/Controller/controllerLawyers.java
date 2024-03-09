package com.example.Advogados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Services.serviceLawyers;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lawyers")
public class controllerLawyers {

    @Autowired
    serviceLawyers service;

    @PostMapping("saveLawyers")
    public ResponseEntity<?> saveLawyers(@RequestBody Lawyers lawyers, BindingResult result) {
        return service.saveLawyers(lawyers, result);
    }

}
