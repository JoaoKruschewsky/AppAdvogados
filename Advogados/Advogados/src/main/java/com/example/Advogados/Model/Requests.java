package com.example.Advogados.Model;

import java.time.Instant;
import java.time.LocalDate;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
