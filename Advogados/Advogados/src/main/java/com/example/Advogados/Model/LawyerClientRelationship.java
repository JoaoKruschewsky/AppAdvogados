package com.example.Advogados.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Lawyer_Client_Relationship")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LawyerClientRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "laywer_id")
    private Lawyers lawyer;

    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

}
