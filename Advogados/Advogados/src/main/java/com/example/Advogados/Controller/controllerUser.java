package com.example.Advogados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.User.LoginUserDTO;
import com.example.Advogados.Model.DTO.User.UpdateUserDTO;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.Services.CRUDuser.LoginUserService;
import com.example.Advogados.Services.CRUDuser.SaveUserService;
import com.example.Advogados.Services.interfaces.User.UpdateUser;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class ControllerUser {

    private RepositoryUser action;
    private SaveUserService saveService;
    private LoginUserService loginService;
    private UpdateUser updateUser;

    @Autowired
    public void setWired(RepositoryUser action, SaveUserService saveService, LoginUserService loginService,
            UpdateUser updateUser) {
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
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> saveimg(@PathVariable Long id, @RequestBody UpdateUserDTO updateDTO) {
        return updateUser.updateUser(id, updateDTO);
    }

    /*
     * @PostMapping("verifyUser")
     * public ResponseEntity<?> verifyUser(@RequestBody LoginDTO user) {
     * return loginService.verifyLoginUser(user);
     * }
     */

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
