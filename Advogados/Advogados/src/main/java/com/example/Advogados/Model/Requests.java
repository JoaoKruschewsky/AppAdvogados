package com.example.Advogados.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "AllRequests")
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lawyer_id")
    private Lawyers lawyer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String changeRelation;


    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lawyers getLawyers() {
        return lawyer;
    }

    public void setLawyers(Lawyers lawyers) {
        this.lawyer = lawyers;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User users) {
        this.user = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getChangeRelation() {
        return changeRelation;
    }

    public void setChangeRelation(String changeRelation) {
        this.changeRelation = changeRelation;
    }

}