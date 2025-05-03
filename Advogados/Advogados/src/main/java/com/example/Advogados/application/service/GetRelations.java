package com.example.Advogados.application.service;

import java.util.List;

public interface GetRelations {
    
    public List<Object> ReadUser(final Long id);

    public List<Object> ReadLawyer(final Long id);
}
