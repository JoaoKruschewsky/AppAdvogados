package com.example.Advogados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryUser;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class controllerUser {
    
    @Autowired
    private repositoryUser action;

@PostMapping("saveUser")
public User saveUser( @RequestBody User user){
    return action.save( user);
}

@GetMapping("getUsers")
public List<User> getUser(){
    return action.findAll();
}
}
