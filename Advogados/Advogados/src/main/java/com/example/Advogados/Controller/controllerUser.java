package com.example.Advogados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<?> saveUser(@RequestBody User user, BindingResult result) {
        return service.saveUser(user, result);
    }

    @PostMapping("verifyUser")
    public ResponseEntity<?> verifyUser(@RequestBody User user) {
        return service.verifyUser(user);
    }

    @GetMapping("getUser")
    public List<User> getUser() {
        return action.findAll();
    }

    @GetMapping("getUserbyName/{name}")
    public List<User> getUserEmail(@PathVariable String name) {
        return action.findByname(name);
    }

}
