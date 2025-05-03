package com.example.Advogados.application.service.impl;

import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.User.UpdateUserDTO;
import com.example.Advogados.Model.DTO.User.UserDto;
import com.example.Advogados.Model.Role;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.Repository.RolesRepository;
import com.example.Advogados.application.service.UserControl;
import com.example.Advogados.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Service
public class UserControlImpl implements UserControl {
    private final RepositoryUser actionUser;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserControlImpl(RepositoryUser actionUser, BCryptPasswordEncoder passwordEncoder, RolesRepository rolesRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.actionUser = actionUser;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ResponseEntity<?> updateUser(final long id, UpdateUserDTO body) {
        Optional<User> optionalUser = actionUser.findById(id);

        userMapper.updateUser(body, optionalUser.get());
        actionUser.save(optionalUser.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> saveUser(UserDto body) {
        Optional<User> verifyUser = actionUser.findByEmail(body.email());
        if (verifyUser.isPresent()) {
            throw new  ResponseStatusException(HttpStatus.UNAUTHORIZED, "User exist");
        }
            User user =  userMapper.toUser(body);
            var roleUser = rolesRepository.findByName(Role.Values.USER.name());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Set.of(roleUser));
            actionUser.save(user);
            return new ResponseEntity<>(HttpStatus.OK);

        }

    @Override
    public ResponseEntity<?> loginUser(LoginDTO user) {
        Optional<User> existingUser = actionUser.findByEmail(user.getEmail());
        if (!(existingUser.isPresent() && existingUser.get().isLoginCorrect(user, bCryptPasswordEncoder))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unregistered user or invalid credentials");
        }        return ResponseEntity.ok().build();
    }
}