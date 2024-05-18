package com.example.Advogados.Services.CRUDuser;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.User.LoginUserDTO;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.User.loginUserInterface;
import com.example.Advogados.message.Message;

@Service
public class LoginUserService implements loginUserInterface {

    private repositoryUser actionUser;
    private Message msg;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static Logger logger = LoggerFactory.getLogger(LoginUserService.class);

    @Autowired
    public void setWired(repositoryUser actionUser, Message msg, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.actionUser = actionUser;
        this.msg = msg;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User verifyLoginUser(LoginDTO user) {
        Optional<User> existingUser = actionUser.findByEmail(user.getEmail());

        if (existingUser.isPresent() && existingUser.get().isLoginCorrect(user, bCryptPasswordEncoder)) {
            // msg.setMensagem("login aceito Usuario.");
            return existingUser.get();
        }
        // msg.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
        return null;
    }

}
