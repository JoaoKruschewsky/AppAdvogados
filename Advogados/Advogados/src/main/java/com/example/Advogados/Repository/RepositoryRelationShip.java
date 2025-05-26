package com.example.Advogados.Repository;

import java.util.List;
import java.util.Optional;

import com.example.Advogados.domains.LawyerClientRelationship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryRelationShip extends CrudRepository<LawyerClientRelationship, Long> {

    List<LawyerClientRelationship> findAllLawyerClientRelationshipsByClientId(final long id);

    Optional<LawyerClientRelationship> findClientById(final long id);

    Optional<LawyerClientRelationship> findLawyerClientRelationshipByClientIdAndLawyerId(final long id,
            final long idLawyer);


    Optional<LawyerClientRelationship> findById(final long id);


    Optional<LawyerClientRelationship> findLawyerById(final long id);

    List<LawyerClientRelationship> findAllLawyerClientRelationshipsByLawyerId(final long id);

    List<LawyerClientRelationship> findRelationship(final long id);
    List<LawyerClientRelationship> findRelationshipUser(final long id);
    List<LawyerClientRelationship> findRelationshipLawyer(final long id);

    Optional<LawyerClientRelationship> findLawyerById(final Long id);

}
