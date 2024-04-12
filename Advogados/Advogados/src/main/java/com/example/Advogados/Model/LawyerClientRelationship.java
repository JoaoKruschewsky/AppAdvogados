package com.example.Advogados.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lawyer_Client_Relationship")
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Lawyers getLawyer() {
        return lawyer;
    }

    public void setLawyer(Lawyers lawyer) {
        this.lawyer = lawyer;
    }
}
