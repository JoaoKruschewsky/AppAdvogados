package com.example.Advogados.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.Advogados.Model.User;

public interface repositoryUser extends CrudRepository <User, Long> {
    

    List<User> findAll();
}
