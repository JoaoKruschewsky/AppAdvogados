package com.example.Advogados.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.User;

@Repository
public interface repositoryRelationShip extends CrudRepository<LawyerClientRelationship, Long> {

    LawyerClientRelationship findByClient(User user);
}
