package com.example.Advogados.Services.CRUDrequests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.apache.catalina.connector.Response;
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
import com.example.Advogados.Repository.RepositoryRequests;
import com.example.Advogados.message.Message;

@ExtendWith(MockitoExtension.class)
public class DropRequestsTest {

    @Mock
    private RepositoryRequests action;

    @Mock
    private Message msg;

    @InjectMocks
    DropRequests dropRequests;

    public User createUser() {
        return new User(1L, "21312312", "joao", "213123123", "pedro@gmail.com", null, null, "12345678", null, null);
    }

    public Lawyers createLawyers() {
        return new Lawyers(1L, "2312312", "krel", null, null, null, null, null, null, null, "12312312",
                null, "joao@gmail.com", "12345678", null);

    }

    @Test
    void testDrop() {
        Requests requests = new Requests(1L, this.createLawyers(), this.createUser(), "em andamento", "pendente");
        Requests requestsTwo = new Requests(2L, this.createLawyers(), this.createUser(), "em andamento", "pendente");

        ArrayList<Long> listRequests = new ArrayList<>();
        listRequests.add(requests.getId());
        listRequests.add(requestsTwo.getId());

        ResponseEntity<?> response = dropRequests.drop(listRequests);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        msg.setMensagem("Solicitacoes apagada com sucesso");
        assertEquals(response.getBody(), msg);
    }
}
