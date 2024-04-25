package com.example.Advogados.Services.interfaces.update;

import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.updateUserDTO;

public interface updateUser {
    
    public ResponseEntity<?> updateUser(Long id, updateUserDTO updateUserDTO);

}
