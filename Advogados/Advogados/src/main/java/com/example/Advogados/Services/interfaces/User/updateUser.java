package com.example.Advogados.Services.interfaces.User;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.DTO.User.UpdateUserDTO;

public interface UpdateUser {

    public ResponseEntity<?> updateUser(Long id, UpdateUserDTO updateUserDTO);

}
