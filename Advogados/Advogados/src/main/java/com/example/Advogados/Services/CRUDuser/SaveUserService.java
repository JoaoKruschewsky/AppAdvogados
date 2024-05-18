package com.example.Advogados.Services.CRUDuser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Role;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.RolesRepository;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.infra.RestExpectionHandler;
import com.example.Advogados.Services.interfaces.User.verifySaveUser;
import com.example.Advogados.message.Message;

@Service
public class SaveUserService implements verifySaveUser {
    private repositoryUser actionUser;
    private Message msg;
    private BCryptPasswordEncoder passwordEncoder;
    private RolesRepository rolesRepository;

    @Autowired
    public void setWired(repositoryUser actionUser, Message msg, RolesRepository rolesRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.actionUser = actionUser;
        this.msg = msg;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> verifyUser(User user) {
        Optional<User> verifyUser = actionUser.findByEmail(user.getEmail());
        // User verifyphoneNumber = actionUser.findByphoneNumber(user.getPhoneNumber());
        if (!verifyUser.isEmpty()) {

            msg.setMensagem("Já existe um email cadastrado");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } else {

            var roleUser = rolesRepository.findByName(Role.Values.USER.name());

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(roleUser);

            msg.setMensagem("Nenhum usuario encontrado, Cadastro Aceito " + actionUser.save(user));

            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }
}
