package com.example.Advogados.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Lawyer_Client_Relationship")
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

    private LocalDate dateCreateRelation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    public LawyerClientRelationship(Long id, Lawyers lawyer, String status, LocalDate dateCreateRelation, User client) {
        this.id = id;
        this.lawyer = lawyer;
        this.status = status;
        this.dateCreateRelation = dateCreateRelation;
        this.client = client;
    }

    @PrePersist
    protected void onCreate() {
        dateCreateRelation = LocalDate.now();
    }


}
