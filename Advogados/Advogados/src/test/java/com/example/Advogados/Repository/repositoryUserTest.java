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

        String email = "2sdasd@gmail.com";
        this.createUser(email);

        Optional<User> foundedUser = this.repositoryUser.findByEmail(email);

        assertThat(foundedUser.isPresent()).isTrue();
    }

    private User createUser(User user) {
        User newUser = new User(user);
        
        newUser.setEmail(email);
        return newUser;
    }
}
