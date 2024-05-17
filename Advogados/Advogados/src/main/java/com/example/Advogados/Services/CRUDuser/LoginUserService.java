package com.example.Advogados.Services.CRUDuser;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.DTO.User.LoginUserDTO;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.User.loginUserInterface;
import com.example.Advogados.message.Message;

@Service
public class LoginUserService implements loginUserInterface, UserDetailsService {

    private repositoryUser actionUser;
    private Message msg;

    private static Logger logger = LoggerFactory.getLogger(LoginUserService.class);

    @Autowired
    public void setWired(repositoryUser actionUser, Message msg) {
        this.actionUser = actionUser;
        this.msg = msg;
    }

    @Override
    public ResponseEntity<?> verifyLoginUser(LoginUserDTO user) {
        Optional<User> existingUser = actionUser.findByEmail(user.getEmailDTO());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPasswordDTO())) {
            // msg.setMensagem("login aceito Usuario.");
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            // msg.setMensagem("Usuário não cadastrado ou credenciais inválidas.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional<User> user = actionUser.findByEmail(username);

        if (user.isEmpty()) {
            logger.error("User not found " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + username);
        return user.get();
    }
}
