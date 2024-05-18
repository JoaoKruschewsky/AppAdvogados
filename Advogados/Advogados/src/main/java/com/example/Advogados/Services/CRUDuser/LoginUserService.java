package com.example.Advogados.Services.CRUDuser;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Model.UserAndLawyer;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.User.LoginUserDTO;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.User.LoginUserInterface;
import com.example.Advogados.message.Message;

@Service
public class LoginUserService implements LoginUserInterface {

    private repositoryUser actionUser;
    private Message msg;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private repositoryLawyers actionLawyer;

    private static Logger logger = LoggerFactory.getLogger(LoginUserService.class);

    @Autowired
    public void setWired(repositoryUser actionUser, Message msg, BCryptPasswordEncoder bCryptPasswordEncoder,
            repositoryLawyers actionLawyer) {
        this.actionUser = actionUser;
        this.msg = msg;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.actionLawyer = actionLawyer;
    }

    @Override
    public UserAndLawyer verifyLoginUser(LoginDTO user) {
        Optional<User> existingUser = actionUser.findByEmail(user.getEmail());
        Optional<Lawyers> existingLawyer = actionLawyer.findByEmail(user.getEmail());
        if (existingUser.isPresent() && existingUser.get().isLoginCorrect(user, bCryptPasswordEncoder)) {
            // msg.setMensagem("Accepted Login Lawyer.");
            return new UserAndLawyer(existingUser.get(), null);
        }
        if (existingLawyer.isPresent() && existingLawyer.get().isLoginCorrect(user, bCryptPasswordEncoder)) {
            // msg.setMensagem("Accept Login Lawyer.");
            return new UserAndLawyer(null, existingLawyer.get());
        }
        // msg.setMensagem("Incorrect User");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

}
