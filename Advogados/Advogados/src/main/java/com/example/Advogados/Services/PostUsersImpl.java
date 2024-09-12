package com.example.Advogados.Services;


import com.example.Advogados.Model.DTO.LawyerUserDTO;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.UserAndLawyer;
import com.example.Advogados.Repository.RepositoryLawyers;
import com.example.Advogados.Repository.RepositoryUser;
import com.example.Advogados.Services.interfaces.PostUser;
import com.example.Advogados.mapper.LawyerMapper;
import com.example.Advogados.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostUsersImpl implements PostUser {

    private final RepositoryUser repositoryUser;
    private final RepositoryLawyers repositoryLawyers;
    private final LawyerMapper lawyerMapper;
    private final UserMapper userMapper;

    public PostUsersImpl(RepositoryUser repositoryUser, RepositoryLawyers repositoryLawyers, LawyerMapper lawyerMapper, UserMapper userMapper) {
        this.repositoryUser = repositoryUser;
        this.repositoryLawyers = repositoryLawyers;
        this.lawyerMapper = lawyerMapper;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<HttpStatus> saveLawyer(LawyerUserDTO lawyerUserDTO) {
        UserAndLawyer lawyers =  lawyerMapper.toLawyer(lawyerUserDTO);

        repositoryLawyers.save(lawyers.getLawyer());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> saveUser(LawyerUserDTO lawyerUserDTO) {
       UserAndLawyer user = userMapper.toUser(lawyerUserDTO);

       repositoryUser.save(user.getUser());

       return new ResponseEntity<>(HttpStatus.OK);
    }
}
