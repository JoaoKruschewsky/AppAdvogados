package com.example.Advogados.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.User;

@Repository
public interface repositoryUser extends CrudRepository<User, Long> {

    List<User> findBynameStartingWithIgnoreCase(String name);

    User findByEmail(String emal);

    User findByPassword(String password);

    User findBycpf(String cpf);

    User findByphoneNumber(String phoneString);

    List<User> findAll();

    @Modifying
    @Query(value = "UPDATE Users SET value = ? WHERE img_profile = ? ", nativeQuery = true)
    void setaramout(int quantidade, String name);
}
