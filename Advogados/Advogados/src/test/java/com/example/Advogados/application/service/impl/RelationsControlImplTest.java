package com.example.Advogados.application.service.impl;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Repository.RepositoryRelationShip;
import com.example.Advogados.application.service.RelationsControl;
import com.example.Advogados.domains.response.RelationShipResponse;
import com.example.Advogados.helpers.ModelsTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RelationsControlImplTest {

    @Mock
    private RepositoryRelationShip repositoryRelationShip;

    @InjectMocks
    private RelationsControl relationsControl;

    public LawyerClientRelationship createRelation() {
        return new LawyerClientRelationship(1L, ModelsTests.createlawyers(), "Em andamento", LocalDate.now(),
                ModelsTests.createUsers());
    }
    @Nested
    @DisplayName("Get relations")
    public class GetRelations {

        @Test
        void getRelations() {

            List<LawyerClientRelationship> relations = new ArrayList<>();
            relations.add(createRelation());

            List<RelationShipResponse> relationShipResponses = new ArrayList<>();


            when(relationsControl.getRelations(1L)).thenReturn(relationShipResponses);
        }

    }

}