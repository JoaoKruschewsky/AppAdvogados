package com.example.Advogados.Services.testRelation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.CRUDrelations.ReadRelations;
import com.example.Advogados.Services.CRUDrelations.saveRelation;
import com.example.Advogados.message.Message;

import lombok.var;

@ExtendWith(MockitoExtension.class)
public class RelationTest {

    @Mock
    private Message message;

    @Mock
    private repositoryUser actionUser;

    @Mock
    private repositoryLawyers actionLawyers;

    @Mock
    private repositoryRelationShip actionRelation;

    @Autowired
    @InjectMocks
    private ReadRelations relationReadLawyer;

    @Autowired
    @InjectMocks
    private saveRelation saveRelation;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        new ReadRelationLawyer();
    }

    public User createUser() {
        return new User(1L, "21312312", "joao", "213123123", "pedro@gmail.com", null, null, "12345678", null);
    }

    public Lawyers createLawyers() {
        return new Lawyers(1L, "2312312", "krel", null, null, null, null, null, null, null, "12312312",
                null, "joao@gmail.com", "12345678");

    }

    public LawyerClientRelationship createRelation() {
        return new LawyerClientRelationship(1L, this.createLawyers(), "Concluido", this.createUser());
    }

    @Nested
    @DisplayName("Mostrar todos as relacoes do advogado")
    class ReadRelationLawyer {

        @Test
        void readRelationTest() {

            LawyerClientRelationship relationNew = createRelation();

            List<LawyerClientRelationship> listRelation = new ArrayList<>();
            listRelation.add(relationNew);

            when(actionRelation.findAllLawyerClientRelationshipsByLawyerId(1L))
                    .thenReturn(listRelation);

            ResponseEntity<?> result = relationReadLawyer.ReadUser(1L);

            assertEquals(HttpStatus.OK, result.getStatusCode());

            assertThat(result.getBody());

        }

    }

    @Nested
    @DisplayName("Salvando Relacoes")
    class SaveRelation {

        @Test
        void SaveRelationTeste() {

            when(actionUser.findById(createRelation().getClient().getId())).thenReturn(Optional.of(createUser()));

            when(actionLawyers.findById(createRelation().getLawyer().getId())).thenReturn(Optional.of(createLawyers()));

            ResponseEntity<?> save = saveRelation.saveNewRelation(createRelation());

            assertEquals(HttpStatus.OK, save.getStatusCode());

            message.setMensagem("Relacao salva entre " + new ArrayList<>());

            assertEquals(save.getBody(), message);

        }
    }
}
