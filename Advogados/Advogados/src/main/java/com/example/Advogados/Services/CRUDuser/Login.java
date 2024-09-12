package com.example.Advogados.Services.CRUDuser;

import java.util.Optional;

import lombok.AllArgsConstructor;
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
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.Services.interfaces.User.LoginUI;
import com.example.Advogados.message.Message;

@Service
@AllArgsConstructor
public class Login implements LoginUI {

    private final RepositoryUser actionUser;
    private final Message msg;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RepositoryLawyers actionLawyer;

    private static Logger logger = LoggerFactory.getLogger(Login.class);

    @Override
    public UserAndLawyer verifyLogin(LoginDTO newUser) {
        Optional<User> existingUser = actionUser.findByEmail(newUser.getEmail());
        Optional<Lawyers> existingLawyer = actionLawyer.findByEmail(newUser.getEmail());
        if (existingUser.isPresent() && existingUser.get().isLoginCorrect(newUser, bCryptPasswordEncoder)) {
            // msg.setMensagem("Accepted Login Lawyer.");
            return new UserAndLawyer(existingUser.get(), null);
        }
        if (existingLawyer.isPresent() && existingLawyer.get().isLoginCorrect(newUser, bCryptPasswordEncoder)) {
            // msg.setMensagem("Accept Login Lawyer.");
            return new UserAndLawyer(null, existingLawyer.get());
        }
        // msg.setMensagem("Incorrect User");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

}
