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
import com.example.Advogados.Model.DTO.User.LoginUserDTO;
import com.example.Advogados.Model.DTO.User.updateUserDTO;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.CRUDuser.LoginUserService;
import com.example.Advogados.Services.CRUDuser.SaveUserService;
import com.example.Advogados.Services.interfaces.User.updateUser;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class ControllerUser {

    private repositoryUser action;
    private SaveUserService saveService;
    private LoginUserService loginService;
    private updateUser updateUser;

    @Autowired
    public void setWired(repositoryUser action, SaveUserService saveService, LoginUserService loginService,
            updateUser updateUser) {
        this.action = action;
        this.saveService = saveService;
        this.loginService = loginService;
        this.updateUser = updateUser;
    }

    @PostMapping("saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return saveService.verifyUser(user);
    }

    @PostMapping("saveUpdatesUser/{id}")
    public ResponseEntity<?> saveimg(@PathVariable Long id, @RequestBody updateUserDTO updateDTO) {
        return updateUser.updateUser(id, updateDTO);
    }

    @PostMapping("verifyUser")
    public ResponseEntity<?> verifyUser(@RequestBody LoginUserDTO user) {
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
