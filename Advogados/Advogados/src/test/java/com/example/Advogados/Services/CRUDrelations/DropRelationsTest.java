package com.example.Advogados.Services.CRUDrelations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.LawyerClientRelationship;
import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryRelationShip;
import com.example.Advogados.message.Message;

@ExtendWith(MockitoExtension.class)
public class DropRelationsTest {

    @Mock
    private repositoryRelationShip action;

    @InjectMocks
    private DropRelations dropRelations;

    @Mock
    private Message msg;

    @Test
    void testDrop() {

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        ResponseEntity response = dropRelations.drop(ids);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        msg.setMensagem("Relaceos apagada com sucesso.");
        assertEquals(msg, response.getBody());
    }
}
