package com.example.Advogados.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.Role;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {

}
