package com.example.Advogados.Services.CRUDlawyer;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.DTO.Lawyer.UpdateLawyerDTO;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Services.interfaces.lawyer.UptadeLawyer;
import com.example.Advogados.message.Message;

@Service
public class UpdateLawyerService implements UptadeLawyer {

    private RepositoryLawyers action;
    private Message message;

    @Autowired
    public void setWired(RepositoryLawyers action, Message message) {
        this.action = action;
        this.message = message;
    }

    public ResponseEntity<?> updateLawyer(Long id, UpdateLawyerDTO updateDTO, JwtAuthenticationToken token) {

        Optional<Lawyers> optionalLawyer = action.findById(id);

        if (optionalLawyer.isEmpty()) {
            throw new NoSuchElementException(" No found Lawyer by id");
        }

        if (id.equals(Long.parseLong(token.getName()))) {
            optionalLawyer.get().setDescricion(updateDTO.getDescricionDTO());
            optionalLawyer.get().setImg_profile(updateDTO.getImgDTO());
            optionalLawyer.get().setPrice(updateDTO.getPriceDTO());
            optionalLawyer.get().setSpecializedAir(updateDTO.getSpecializedAirDTO());
            optionalLawyer.get().setTitleLawyers(updateDTO.getTitleLawyerDTO());
            action.save(optionalLawyer.get());
            message.setMensagem("Atualizacoes salva com Sucesso");
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.setMensagem("Ocorreu um erro tente novamente" + optionalLawyer.get());
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
