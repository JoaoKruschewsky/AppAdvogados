package com.example.Advogados.Services.updateService;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.updateLawyerDTO;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Services.interfaces.update.uptadeLawyer;
import com.example.Advogados.message.message;

@Service
public class updateLawyerService implements uptadeLawyer{
    
    private repositoryLawyers action;
    private message message;

    @Autowired
    public void setWired(repositoryLawyers action, message message){
        this.action = action;
        this.message = message;
    }


    public ResponseEntity<?> updateLawyer(Long id, updateLawyerDTO updateDTO){
        Optional<Lawyers> optionalLawyer = action.findById(id);

        if (optionalLawyer.isPresent()) {
            optionalLawyer.get().setDescricion(updateDTO.getDescricionDTO());
            optionalLawyer.get().setImg_Profile(updateDTO.getImgDTO());
            optionalLawyer.get().setPrice(updateDTO.getPriceDTO());
            optionalLawyer.get().setSpecializedAir(updateDTO.getSpecializedAirDTO());
            optionalLawyer.get().setTitleLawyers(updateDTO.getTitleLawyerDTO());
            return new ResponseEntity<>(action.save(optionalLawyer.get()), HttpStatus.OK);
        } else {
            message.setMensagem("Ocorreu um erro tente novamente");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

    }
}
