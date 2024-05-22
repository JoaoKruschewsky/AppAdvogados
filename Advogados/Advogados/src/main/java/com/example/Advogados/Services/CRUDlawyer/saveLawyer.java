package com.example.Advogados.Services.CRUDlawyer;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.Role;
import com.example.Advogados.Repository.RolesRepository;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Services.interfaces.lawyer.VerifySaveLawyer;
import com.example.Advogados.message.Message;

@Service
public class SaveLawyer implements VerifySaveLawyer {

    private RepositoryLawyers actionLawyers;
    private Message msg;
    private BCryptPasswordEncoder passwordEncoder;
    private RolesRepository rolesRepository;

    @Autowired
    public void setWired(RepositoryLawyers actionLawyers, Message msg, BCryptPasswordEncoder passwordEncoder,
            RolesRepository rolesRepository) {
        this.actionLawyers = actionLawyers;
        this.msg = msg;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;

    }

   

    public ResponseEntity<?> verifySaveLawyer(Lawyers lawyers) {

        Optional<Lawyers> verifyLawyers = actionLawyers.findByEmail(lawyers.getEmail());
        if (!verifyLawyers.isEmpty()) {
            msg.setMensagem("Já existe um email cadastrado");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } else {
            var roleUser = rolesRepository.findByName(Role.Values.LAWYER.name());

            lawyers.setPassword(passwordEncoder.encode(lawyers.getPassword()));
            lawyers.setRoles(Set.of(roleUser));
            msg.setMensagem("Nenhum usuario encontrado, Cadastro Aceito " + actionLawyers.save(lawyers));

            return new ResponseEntity<>(msg, HttpStatus.OK);
        }

    }

}
