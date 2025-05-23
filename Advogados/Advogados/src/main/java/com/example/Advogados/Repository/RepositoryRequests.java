package com.example.Advogados.Repository;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import com.example.Advogados.domains.Requests;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryRequests extends CrudRepository<Requests, Long> {

    List<Requests> findRequestsByUserId(final Long id);

    // Optional<Requests> findRequestsByUserId(final Long id);

    Optional<Requests> findById(final Long id);

    // Optional<String> findRequestsByUserId(final Long id);

    List<String> findStatusByUserId(final Long id);

    List<Requests> findRequestsByLawyerId(final Long id);

    // Optional<String> findByStatusByUserId(final Long id);

}
