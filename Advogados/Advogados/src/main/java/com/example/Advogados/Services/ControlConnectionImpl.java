package com.example.Advogados.Services;

import com.example.Advogados.Model.DTO.LawyerUserDTO;
import com.example.Advogados.Model.DTO.LoginDTO;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.Role;
import com.example.Advogados.Model.User;
import com.example.Advogados.Model.UserAndLawyer;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.Services.CRUDuser.Login;
import com.example.Advogados.Services.interfaces.ControlService;
import com.example.Advogados.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ControlConnectionImpl implements ControlService {

    private final RepositoryUser actionUser;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RepositoryLawyers actionLawyer;
    private final UserMapper toUser;
    private static Logger logger = LoggerFactory.getLogger(Login.class);

    @Override
    public ResponseEntity<?>  verifySave(LawyerUserDTO lawyerUserDTO) {
        Optional<User> existingUser = actionUser.findByEmail(lawyerUserDTO.userDTO().email());
        Optional<Lawyers> existingLawyer = actionLawyer.findByEmail(lawyerUserDTO.lawyerDTO().email());
        if (existingLawyer.isPresent()){
            return ResponseEntity.ok().body(existingLawyer.get());
        }
        if (existingUser.isPresent()){
            return ResponseEntity.ok().body(existingUser.get());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

//        if (existingUser.isPresent()){
//           User user = toUser(lawyerUserDTO.userDTO());
//            var roleUser = rolesRepository.findByName(Role.Values.USER.name());
//
//            System.out.println(roleUser);
//
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            user.setRoles(Set.of(roleUser));
//            msg.setMensagem("Nenhum usuario encontrado, Cadastro Aceito " +
//                    actionUser.save(user));
//
//            return new ResponseEntity<>(msg, HttpStatus.OK);
//
//        }
    }
}
