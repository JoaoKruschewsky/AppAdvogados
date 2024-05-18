package com.example.Advogados.Repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.Role;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {

    Set<Role> findByName(String name);
}
