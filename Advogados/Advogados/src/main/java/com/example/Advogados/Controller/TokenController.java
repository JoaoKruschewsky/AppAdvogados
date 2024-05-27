package com.example.Advogados.Controller;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.Role;
import com.example.Advogados.Model.User;
import com.example.Advogados.Model.UserAndLawyer;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.LoginResponse;
import com.example.Advogados.Services.CRUDuser.Login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Login JuríConecta")
public class TokenController {

    private final JwtEncoder jwtEncoder;

    @Autowired
    private Login loginUser;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Operation(summary = "login to JuríConecta\r\n" + //
            "")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "\r\n" + //
            "User verified and receives a token", content = @Content(mediaType = "application/json", examples = @ExampleObject("{ \"token\" : \"token\",\"InstantSec\":300}"))),
            @ApiResponse(responseCode = "401", description = "User incorreto, retonar uma excpetion BadCredentials", content = @Content(mediaType = "application/json", examples = @ExampleObject("User incorreto, retonar uma excpetion BadCredentials")))

    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO login) {

        UserAndLawyer user = loginUser.verifyLogin(login);
        UserAndLawyer lawyers = loginUser.verifyLogin(login);

        if (user.getUser() != null) {

            var now = Instant.now();
            var expiresIn = 300L;

            var scopes = user.getUser().getRoles()
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.joining(" "));

            var claims = JwtClaimsSet.builder().issuer("mybackend").subject(user.getUser().getId().toString())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiresIn))
                    .claim("scope", scopes)
                    .build();

            var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn, user.getUser(), null));
        } else if (lawyers.getLawyer() != null) {
            var now = Instant.now();
            var expiresIn = 300L;

            var scopes = lawyers.getLawyer().getRoles()
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.joining(" "));

            var claims = JwtClaimsSet.builder().issuer("mybackend").subject(lawyers.getLawyer().getId().toString())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiresIn))
                    .claim("scope", scopes)
                    .build();

            var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn, null, lawyers.getLawyer()));
        } else {
            throw new BadCredentialsException("Incorret user");

        }

    }

}