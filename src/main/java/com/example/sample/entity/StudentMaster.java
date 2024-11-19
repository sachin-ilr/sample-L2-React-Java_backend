package com.example.sample.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "student_master")
public class StudentMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT", name = "firstname")
    private String firstname;

    @Column(columnDefinition = "TEXT", name = "lastname")
    private String lastname;

    @Column(columnDefinition = "TEXT", name = "roleno")
    private String roleno;

    @Column(columnDefinition = "TEXT", name = "subjectname")
    private String subjectname;

    @Column(columnDefinition = "TEXT", name = "staffname")
    private String staffname;

    @Column(columnDefinition = "TEXT", name = "subjectcode")
    private String subjectcode;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        modifiedDate = null;
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDateTime.now();
    }
}