package com.example.Advogados.Services;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Model.updateDTO;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.message.message;

@Service
public class serviceLawyers {

    @Autowired
    repositoryLawyers action;

    @Autowired
    message message;

    public ResponseEntity<?> uptade(Long ID, updateDTO updateDTO) {
        Optional<Lawyers> optionalUser = action.findById(ID);

        if (optionalUser.isPresent()) {
            Lawyers Lawyers = optionalUser.get();
            Lawyers.setDescricion(updateDTO.getDescricionDTO());
            Lawyers.setImg_Profile(updateDTO.getImgDTO());
            Lawyers.setPrice(updateDTO.getPriceDTO());
            Lawyers.setSpecializedAir(updateDTO.getSpecializedAirDTO());
            Lawyers.setTitleLawyers(updateDTO.getTitleLawyerDTO());
            return new ResponseEntity<>(action.save(Lawyers), HttpStatus.OK);
        } else {
            message.setMensagem("Ocorreu um erro tente novamente");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

    }
}
