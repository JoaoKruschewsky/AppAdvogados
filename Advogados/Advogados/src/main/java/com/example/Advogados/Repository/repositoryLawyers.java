package com.example.Advogados.Repository;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;

@Repository
public interface repositoryLawyers extends CrudRepository<Lawyers, Long> {

    Optional<Lawyers> findByEmail(String email);

    Lawyers findBycpf(String cpf);

    Optional<Lawyers> findById(Long id);

    List<Lawyers> findBynameStartingWithIgnoreCase(String name);

    Lawyers findByphoneNumber(String phoneString);

    List<Lawyers> findAll();
}
