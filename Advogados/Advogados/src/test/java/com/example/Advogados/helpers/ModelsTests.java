package com.example.Advogados.helpers;

import com.example.Advogados.Model.Lawyers;
import com.example.Advogados.Model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class ModelsTests {

    public static Lawyers createlawyers() {
        Lawyers lawyer = new Lawyers();
        lawyer.setId(1L);
        lawyer.setTitleLawyers("Dr. João da Silva");
        lawyer.setSpecializedAir("Direito Penal");
        lawyer.setDescricion("Advogado com experiência em defesa criminal.");
        lawyer.setCpf("123.456.789-00"); // ou CPF válido fake tipo: 529.982.247-25
        lawyer.setPrice(BigDecimal.valueOf(500.00));
        lawyer.setValidationOAB("OAB123456");

// Supondo que User (pai) tem esses métodos
        lawyer.setEmail("joao.advogado@email.com");
        lawyer.setName("João da Silva");
        lawyer.setPassword("senha123");

        return lawyer;
    }


    public static User createUsers(){
        User user = new User();
        user.setId(1L);
        user.setName("Maria Souza");
        user.setCPF("529.982.247-25"); // CPF válido fake
        user.setPhoneNumber("11 91234-5678");
        user.setEmail("maria.souza@email.com");
        user.setPassword("senha123"); // criptografe se necessário
        user.setImg_Profile("https://cdn.meuapp.com/perfil/maria.jpg");

// Relacionamentos podem ficar vazios em mocks simples
        user.setLawyerRelationships(List.of());
        user.setRequests(List.of());
        user.setRoles(Set.of()); // ou adicionar Role fictícia se quiser testar permissões

        return user;
    }
}
