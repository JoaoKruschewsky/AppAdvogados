package com.example.Advogados.adapters.inbound;

import com.example.Advogados.Model.DTO.User.UpdateUserDTO;
import com.example.Advogados.Model.DTO.User.UserDto;
import com.example.Advogados.application.service.UserControl;
import com.example.Advogados.application.service.impl.UserControlImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@Tag(name = "RegisterDTO User for API JuríConecta")
public class ControllerUser {

    private final UserControl userControl;

    public ControllerUser(UserControlImpl userControl) {
        this.userControl = userControl;
    }

    @Operation(summary = "RegisterDTO User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User able to register\r\n" + //
                    "", content = @Content(mediaType = "application/json", examples = @ExampleObject("User save succesc!"))),
            @ApiResponse(responseCode = "400", description = "Unable to register", content = @Content(mediaType = "application/json", examples = @ExampleObject("Registered email or CPF")))
    })
    @PostMapping(path = "saveUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) {
        return userControl.saveUser(user);
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
        return userControl.updateUser(id, updateDTO);
    }

}
