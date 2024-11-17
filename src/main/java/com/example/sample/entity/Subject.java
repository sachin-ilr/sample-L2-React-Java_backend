package com.example.sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Change from long to Integer

    @NotBlank(message = "name is mandatory.")
    @Column(columnDefinition = "TEXT", name = "name")
    private String name;

    @NotBlank(message = "staffname is mandatory.")
    @Column(columnDefinition = "TEXT", name = "staffname")
    private String staffname;

    @Column(columnDefinition = "TEXT", name = "subjectcode")
    private String subjectcode;
}