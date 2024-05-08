package com.example.Advogados.Services.CRUDrequests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.Requests;
import com.example.Advogados.Model.User;
import com.example.Advogados.Repository.repositoryRequests;
import com.example.Advogados.message.message;

@ExtendWith(MockitoExtension.class)
public class ReadRequestsTest {

    @Mock
    private repositoryRequests action;

    @Mock
    private message msg;

    @InjectMocks
    private ReadRequests readRequests;

     public User createUser() {
        return new User(1L, "21312312", "joao", "213123123", "pedro@gmail.com", null, null, "12345678", null);
    }


    public Lawyers createLawyers() {
        return new Lawyers(1L, "2312312", "krel", null, null, null, null, null, null, null, "12312312",
                null, "joao@gmail.com", "12345678");

    }

    @Nested
    @DisplayName("Leitura das solicitacoes foi sucesso")
    class TestingReadLawyer {
        @Test
        void testReadLawyer() {
             Requests newRequests =  new Requests(createLawyers(),createUser(), "Em andamento", "pendente");
             
            when(action.findRequestsByLawyerId(1L)).thenReturn(List.of(newRequests));

            ResponseEntity<?> read = readRequests.ReadLawyer(1L);

            assertEquals(HttpStatus.OK, read.getStatusCode());

            List<Object> listObject = new ArrayList<>();
            listObject.add("joao");
            listObject.add("pendente");
            listObject.add("Em andamento");
            listObject.add(1);
            assertEquals(read.getBody(), listObject);

        }
    }
}
