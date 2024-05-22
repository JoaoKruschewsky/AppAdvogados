package com.example.Advogados.Services.CRUDuser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.User;
import com.example.Advogados.Model.DTO.User.UpdateUserDTO;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.Services.interfaces.User.UpdateUser;
import com.example.Advogados.message.Message;

@Service
public class UpdateUserService implements UpdateUser {

    private RepositoryUser action;
    private Message message;

    @Autowired
    public void setWired(RepositoryUser action, Message message) {
        this.action = action;
        this.message = message;
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = action.findById(id);

        if (optionalUser.isPresent()) {
            optionalUser.get().setImg_Profile(updateUserDTO.getImgDTO());
            optionalUser.get().setEmail(updateUserDTO.getEmailDTO());
            optionalUser.get().setPhoneNumber(updateUserDTO.getPhoneNumberDTO());
            return new ResponseEntity<>(action.save(optionalUser.get()), HttpStatus.OK);
        } else {
            message.setMensagem("Ocorreu um erro tente novamente");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
