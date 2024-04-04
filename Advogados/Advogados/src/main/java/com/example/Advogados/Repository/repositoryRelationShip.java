package com.example.Advogados.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.User;

@Repository
public interface repositoryRelationShip extends CrudRepository<LawyerClientRelationship, Long> {

    @Query("SELECT u.name FROM User u INNER JOIN LawyerClientRelationship lcr ON lcr.client.id  = u.id WHERE lcr.client.id = :clientId")
    Optional<String> findClientById(@Param("clientId") Long clientId);
}
