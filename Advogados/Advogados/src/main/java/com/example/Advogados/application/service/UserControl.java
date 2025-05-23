package com.example.Advogados.application.service;


import com.example.Advogados.domains.dto.LoginDTO;
import com.example.Advogados.domains.dto.User.UpdateUserDTO;
import com.example.Advogados.domains.dto.User.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public interface UserControl {

    ResponseEntity<?> updateUser(final long id, UpdateUserDTO body);
    ResponseEntity<?> saveUser (UserDto body);
    ResponseEntity<?> loginUser(LoginDTO user);

}
