package com.example.Advogados.Services.registerService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Services.interfaces.email.emailInterface;
import com.example.Advogados.Services.interfaces.savei.verifySaveLawyer;
import com.example.Advogados.message.message;

@Service
public class saveLawyer implements verifySaveLawyer, emailInterface {

    private repositoryLawyers actionLawyers;
    private message msg;
    private JavaMailSender javaMailSender;


    @Autowired
    public void setWired(repositoryLawyers actionLawyers,message msg, JavaMailSender javaMailSender) {
        this.actionLawyers = actionLawyers;
        this.msg = msg;
        this.javaMailSender = javaMailSender;
    }
    Value("${spring.mail.username}")
        private String sender;
        
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

        @
        
        public ResponseEntity<?> emailService(String email, String subject, String message) {
    
            try {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom(sender);
                simpleMailMessage.setTo(email);
                simpleMailMessage.setSubject(subject);
                simpleMailMessage.setText(message);
                javaMailSender.send(simpleMailMessage);
            } catch (Exception e) {
                // TODO: handle exception
            }
    
        }
    

}
