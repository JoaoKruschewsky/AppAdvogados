package com.example.Advogados.Services.testUpdates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;
import com.example.Advogados.Model.DTO.Lawyer.updateLawyerDTO;
import com.example.Advogados.Model.DTO.User.updateUserDTO;
import com.example.Advogados.Repository.repositoryLawyers;
import com.example.Advogados.Repository.repositoryUser;
import com.example.Advogados.Services.CRUDlawyer.updateLawyerService;
import com.example.Advogados.Services.CRUDuser.UpdateUserService;
import com.example.Advogados.Services.interfaces.User.updateUser;
import com.example.Advogados.message.message;

public class UpdateTest {

    @Nested
    class TestUpdateLawyer {

        @Mock
        private repositoryLawyers action;

        @Autowired
        @InjectMocks
        private updateLawyerService updateLawyerService;

        @BeforeEach
        void setup() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        void updateLawyer() {
            updateLawyerDTO lawyerUpdate = new updateLawyerDTO("asdasd", "asdsada", "sra Gabi", "asdsadas",
                    new BigDecimal(32.32));
            Optional<Lawyers> lawyers = Optional
                    .of(new Lawyers(1L, "2312312", "krel", null, null, null, null, null, null, null, "12312312",
                            null, "joao@gmail.com", "12345678"));

            when(action.findById(lawyers.get().getId())).thenReturn(lawyers);

            ResponseEntity<?> result = updateLawyerService.updateLawyer(lawyers.get().getId(), lawyerUpdate);

            verify(action, times(1)).findById(lawyers.get().getId());

            assertEquals(HttpStatus.OK, result.getStatusCode());
        }
    }

    @Nested
    class TestUpdateUser {

        @Mock
        private repositoryUser actionUser;

        @Autowired
        @InjectMocks
        private UpdateUserService updateUserService;

        @BeforeEach
        void setup() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        void updateUser() {
            updateUserDTO uptadeUser = new updateUserDTO("sdas@gmail.com", "123123123", "asdsasad");

            Optional<User> user = Optional.of(new User(1L, "123123123", "sadasd", "23123213", "asdasd", null, null,
                    "saasdsada@gmail.com", "12345678"));

            when(actionUser.findById(user.get().getId())).thenReturn(user);

            ResponseEntity<?> result = updateUserService.updateUser(user.get().getId(), uptadeUser);

            assertEquals(HttpStatus.OK, result.getStatusCode());
        }
    }

}
