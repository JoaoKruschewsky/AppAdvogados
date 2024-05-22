package com.example.Advogados.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
