package com.example.Advogados.Services;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.Advogados.Model.DTO.User.UpdateUserDTO;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.mapper.LawyerMapper;
import com.example.Advogados.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.DTO.Lawyer.UpdateLawyerDTO;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Services.interfaces.lawyer.Update;
import com.example.Advogados.message.Message;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UpdateServiceIpml implements Update {

    private final RepositoryLawyers action;
    private final RepositoryUser userRepo;
    private final Message message;
    private final LawyerMapper lawyerMapper;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<HttpStatus> updateLawyer(Long id, UpdateLawyerDTO updateDTO, JwtAuthenticationToken token) {
        Optional<Lawyers> optionalLawyer = action.findById(id);

        if (optionalLawyer.isEmpty()) {
            throw new NoSuchElementException(" No found Lawyer by id");
        }

        if (id.equals(Long.parseLong(token.getName()))) {

            lawyerMapper.updateLaywer(updateDTO, optionalLawyer.get());
            action.save(optionalLawyer.get());
            return ResponseEntity.ok().build();
        }
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException(" No found Lawyer by id");
        }

            userMapper.updateUser(updateUserDTO, optionalUser.get());
            userRepo.save(optionalUser.get());
            return ResponseEntity.ok().build();

    }
}
