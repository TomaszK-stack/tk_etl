package com.example.tk_etlproc.db.log.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "log")
public class Log {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    private String errorMessage;

    @OneToOne
    @JoinColumn(name = "process_id")
    private Process process;

    @PrePersist
    private void insert(){
        creationDate = new Date();
    }

}