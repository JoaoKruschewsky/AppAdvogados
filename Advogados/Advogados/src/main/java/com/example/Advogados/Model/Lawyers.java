package com.example.Advogados.Model;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Lawyers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lawyers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    private String cpf;

    private String name;
    private String titleLawyers;
    private String specializedAir;
    private String descricion;
    private String img_profile;
    private BigDecimal price;

    @OneToMany(mappedBy = "lawyer")
    @JsonIgnore
    private List<LawyerClientRelationship> clientRelationships;

    @OneToMany(mappedBy = "lawyer")
    @JsonIgnore
    private List<Requests> requests;

    // @Pattern(regexp = "(\\d{2}) \\d{4}-\\d{4}")
    private String phoneNumber;

    // @NotBlank
    private String validationOAB;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    @NotBlank
    private String password;

}
