package com.example.Advogados.Controller;

import java.time.Instant;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.LoginResponse;
import com.example.Advogados.Services.CRUDuser.LoginUserService;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;

    @Autowired
    private LoginUserService loginUser;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO login) {

        User user = loginUser.verifyLoginUser(login);

        if (user == null) {
            throw new BadCredentialsException("Incorret user");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var claims = JwtClaimsSet.builder().issuer("mybackend").subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }

}