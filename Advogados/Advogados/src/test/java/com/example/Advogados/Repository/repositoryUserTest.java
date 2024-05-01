package com.example.Advogados.Repository;

import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import com.example.Advogados.Model.User;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("tes")
public class repositoryUserTest {

    @Autowired
    repositoryUser repositoryUser;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User succes from data base")
    void testFindByEmailSuccess() {

        String email = "asdsadasd@gmail.com";
        this.createUser();

        Optional<User> foundedUser = this.repositoryUser.findByEmail(email);

        assertThat(foundedUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User succes from data base")
    void testFindByEmailCase2() {

        String email = "asdsadasd@gmail.com";

        Optional<User> foundedUser = this.repositoryUser.findByEmail(email);

        assertThat(foundedUser.isEmpty()).isTrue();
    }

    private User createUser() {
        User newUser = new User();
        newUser.setCPF("12312312321");
        newUser.setEmail("asdsadasd@gmail.com");
        newUser.setName("sdasd");
        newUser.setPassword("12345678");
        newUser.setPhoneNumber("2131231231");
        this.entityManager.persist(newUser);

        return newUser;
    }
}
