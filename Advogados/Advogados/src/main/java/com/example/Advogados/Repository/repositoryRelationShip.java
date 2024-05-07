package com.example.Advogados.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;

@Repository
public interface repositoryRelationShip extends CrudRepository<LawyerClientRelationship, Long> {

    /*
     * @Query("SELECT u.name FROM User u INNER JOIN LawyerClientRelationship lcr ON lcr.client.id  = u.id WHERE lcr.client.id = :clientId"
     * )
     * Optional<LawyerClientRelationship> findClientById(@Param("clientId") Long
     * clientId);
     */

    /*
     * @Query("SELECT l.name FROM Lawyers l INNER JOIN LawyerClientRelationship lcr ON lcr.lawyer.id = l.id WHERE lcr.lawyer.id = :lawyerId"
     * )
     * List<String> findLawyerById(@Param("lawyerId") Long lawyerId);
     */

    List<LawyerClientRelationship> findAllLawyerClientRelationshipsByClientId(long id);

    Optional<LawyerClientRelationship> findClientById(long id);
    

    Optional<LawyerClientRelationship> findLawyerById(long id);

    List<LawyerClientRelationship> findAllLawyerClientRelationshipsByLawyerId(long id);

    // Optional<LawyerClientRelationship> findClientById(long id);

    // List<Lawyers> findAllLawyer();

    // Optional<LawyerClientRelationship> findClientById(long id);

    Optional<LawyerClientRelationship> findLawyerById(Long id);

}
