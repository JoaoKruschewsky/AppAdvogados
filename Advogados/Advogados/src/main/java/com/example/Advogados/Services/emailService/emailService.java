package com.example.Advogados.Services.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.Advogados.Services.interfaces.email.emailInterface;


@Service
public class emailService  implements emailInterface{

    private JavaMailSender javaMailSender;

    @Autowired
    public void setWired(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String sender;
    
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
