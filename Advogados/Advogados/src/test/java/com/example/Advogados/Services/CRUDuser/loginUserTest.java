package com.example.Advogados.Services.CRUDuser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.checkerframework.checker.units.qual.A;
import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.UserAndLawyer;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.User.LoginUserDTO;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.message.Message;

public class LoginUserTest {

    @Mock
    private RepositoryUser actionUser;

    @Autowired
    @InjectMocks
    private LoginUserService loginUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Login Incorreto")
    void testVerifyLoginUserCase1() {
        LoginUserDTO newUser = new LoginUserDTO("pedrosda@gmail.com", "12345678");

        when(actionUser.findByEmail(newUser.getEmailDTO())).thenReturn(Optional.empty());

        UserAndLawyer result = loginUser.verifyLoginUser(new LoginDTO(newUser));

        verify(actionUser).findByEmail(newUser.getEmailDTO());

    }

    @Test
    @DisplayName("Login correto")
    void testVerifyLoginUserCase2() {
        LoginUserDTO newUser = new LoginUserDTO("pedro@gmail.com", "12345678");

        Optional<User> user = Optional
                .of(new User(1L, "21312312", "joao", "213123123", "pedro@gmail.com", null, null, "12345678", null,
                        null));

        when(actionUser.findByEmail(newUser.getEmailDTO())).thenReturn(user);

        UserAndLawyer resultCorrect = loginUser.verifyLoginUser(new LoginDTO(newUser));

        verify(actionUser).findByEmail(newUser.getEmailDTO());

        // assertEquals(HttpStatus.OK, resultCorrect.getStatusCode());
    }
}
