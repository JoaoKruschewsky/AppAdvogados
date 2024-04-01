package com.example.Advogados.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;

@Repository
public interface repositoryLawyers extends CrudRepository<Lawyers, Long> {

    Lawyers findByEmail(String email);

    Lawyers findBycpf(String cpf);

    List<Lawyers> findBynameStartingWithIgnoreCase(String name);

    Lawyers findByphoneNumber(String phoneString);

    List<Lawyers> findAll();
}
