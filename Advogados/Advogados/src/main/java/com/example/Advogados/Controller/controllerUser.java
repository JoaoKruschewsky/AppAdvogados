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
import com.example.Advogados.Model.updateDTO;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.servicesUser;
import com.example.Advogados.Services.loginService.loginUser;
import com.example.Advogados.Services.registerService.saveUser;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class controllerUser {

   
    private repositoryUser action;
    private servicesUser service;
    private saveUser saveService;
    private loginUser loginService;

    @Autowired
    public void setWired(repositoryUser action, servicesUser service, saveUser saveService, loginUser loginService){
        this.action = action;
        this.service = service;
        this.saveService = saveService;
        this.loginService = loginService;
    }
    @PostMapping("saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return saveService.verifyUser(user);
    }

    @PostMapping("verifyUser")
    public ResponseEntity<?> verifyUser(@RequestBody User user) {
        return loginService.verifyLoginUser(user);
    }

    /*
     * @PostMapping("saveUpdatesUser/{id}")
     * public ResponseEntity<?> saveimg(@PathVariable Long id, @RequestBody
     * updateDTO updateDTO) {
     * return service.uptade(id, updateDTO);
     * }
     */

    @GetMapping("getUser")
    public List<User> getUser() {
        return action.findAll();
    }

}
