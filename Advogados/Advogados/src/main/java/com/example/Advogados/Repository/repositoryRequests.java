package com.example.Advogados.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Advogados.Model.Requests;

@Repository
public interface repositoryRequests extends CrudRepository<Requests, Long> {

    List<Requests> findAllRequestsByUserId(Long id);
}
