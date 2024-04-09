package com.example.Advogados.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.Requests;

@Repository
public interface repositoryAllRequests extends CrudRepository<Requests, Long> {

}
