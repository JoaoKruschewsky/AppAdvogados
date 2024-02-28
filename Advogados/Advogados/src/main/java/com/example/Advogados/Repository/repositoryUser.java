package com.example.Advogados.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Advogados.Model.User;

public interface repositoryUser extends CrudRepository <User, Long> {
    
}
