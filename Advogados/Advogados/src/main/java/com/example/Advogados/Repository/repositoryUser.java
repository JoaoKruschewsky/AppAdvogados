package com.example.Advogados.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.Advogados.Model.User;

public interface repositoryUser extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findBycpf(String cpf);

    User findByphoneNumber(String phoneString);

    List<User> findAll();
}
