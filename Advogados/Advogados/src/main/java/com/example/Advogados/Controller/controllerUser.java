package com.example.Advogados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.servicesUser;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class controllerUser {

    @Autowired
    private repositoryUser action;

    @Autowired
    private servicesUser service;

    @PostMapping("saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("getUser")
    public List<User> getUser() {
        return action.findAll();
    }
}
