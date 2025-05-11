package com.example.Advogados.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "AllRequests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "lawyer_id")
    private Lawyers lawyer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String changeRelation;

    private String status;

    private LocalDate dateCreateRequests;

    @PrePersist
    protected void onCreate() {
        dateCreateRequests = LocalDate.now();
    }

}
