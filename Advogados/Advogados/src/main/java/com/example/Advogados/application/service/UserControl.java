package com.example.Advogados.application.service;

import com.example.Advogados.Model.DTO.LawyerUserDTO;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.DTO.User.UpdateUserDTO;
import com.example.Advogados.Model.DTO.User.UserDto;
import com.example.Advogados.Model.UserAndLawyer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public interface UserControl {

    ResponseEntity<?> updateUser(final long id, UpdateUserDTO body);
    ResponseEntity<?> saveUser (UserDto body);
    ResponseEntity<?> loginUser(LoginDTO user);

}
