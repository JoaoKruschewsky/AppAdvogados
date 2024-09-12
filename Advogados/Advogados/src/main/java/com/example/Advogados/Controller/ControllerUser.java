package com.example.Advogados.Controller;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.example.Advogados.Services.CRUDuser.Login;
import com.example.Advogados.Services.CRUDuser.SaveUserService;
import com.example.Advogados.Services.interfaces.User.UpdateUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@Tag(name = "RegisterDTO User for API JuríConecta")
public class ControllerUser {

    private SaveUserService saveService;
    private UpdateUser updateUser;

    @Autowired
    public void setWired(SaveUserService saveService,
            UpdateUser updateUser) {
        this.saveService = saveService;
        this.updateUser = updateUser;
    }

    @Operation(summary = "RegisterDTO User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User able to register\r\n" + //
                    "", content = @Content(mediaType = "application/json", examples = @ExampleObject("User save succesc!"))),
            @ApiResponse(responseCode = "400", description = "Unable to register", content = @Content(mediaType = "application/json", examples = @ExampleObject("Registered email or CPF")))
    })
    @PostMapping(path = "saveUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return saveService.verifyUser(user);
    }

    @Operation(summary = "Save Updates Lawyer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save updates"),
            @ApiResponse(responseCode = "401", description = "\r\n" + //
                    "Unauthorized error with access token", content = @Content(mediaType = "application/json", examples = @ExampleObject("\"Unauthorized error with access token")))
    })
    @PostMapping(path = "saveUpdatesUser/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> saveimg(@PathVariable Long id, @RequestBody UpdateUserDTO updateDTO) {
        return updateUser.updateUser(id, updateDTO);
    }

}
