package com.example.Advogados.Services.CRUDuser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.modelDTO.updateLawyerDTO;
import com.example.Advogados.Model.modelDTO.updateUserDTO;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.interfaces.User.updateUser;
import com.example.Advogados.message.message;

@Service
public class updateUserService implements updateUser {
    
    private repositoryUser action;
    private message message;

    @Autowired
    public void setWired(repositoryUser action, message message){
        this.action = action;
        this.message = message;
    }


    public ResponseEntity<?> updateUser(Long id, updateUserDTO updateUserDTO){
        Optional<User> optionalUser = action.findById(id);

        if (optionalUser.isPresent()) {
            optionalUser.get().setEmail(updateUserDTO.getEmailDTO());
            optionalUser.get().setPhoneNumber(updateUserDTO.getPhoneNumberDTO());
            return new ResponseEntity<>(action.save(optionalUser.get()), HttpStatus.OK);
        } else {
            message.setMensagem("Ocorreu um erro tente novamente");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
