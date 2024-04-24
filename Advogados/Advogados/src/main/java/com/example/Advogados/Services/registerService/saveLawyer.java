package com.example.Advogados.Services.registerService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Services.interfaces.savei.verifySaveLawyer;
import com.example.Advogados.message.message;

@Service
public class saveLawyer implements verifySaveLawyer {

    private repositoryLawyers actionLawyers;
    private message msg;

    @Autowired
    public void setWired(repositoryLawyers actionLawyers,message msg) {
        this.actionLawyers = actionLawyers;
        this.msg = msg;
    }
    public ResponseEntity<?> verifySaveLawyer(Lawyers lawyers){
      
            Optional<Lawyers> verifyLawyers = actionLawyers.findByEmail(lawyers.getEmail());
            if (verifyLawyers.get().getEmail() != lawyers.getEmail()) {
                msg.setMensagem("Já existe um email cadastrado");
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            } else {
                msg.setMensagem("Nenhum usuario encontrado, Cadastro Aceito " + actionLawyers.save(lawyers));
    
                return new ResponseEntity<>(msg, HttpStatus.OK);
            }
    
        }
    

}
