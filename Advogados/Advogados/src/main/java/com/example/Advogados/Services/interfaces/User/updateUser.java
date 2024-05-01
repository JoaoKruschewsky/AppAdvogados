package com.example.Advogados.Services.interfaces.User;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.DTO.User.updateUserDTO;

public interface updateUser {

    public ResponseEntity<?> updateUser(Long id, updateUserDTO updateUserDTO);

}
