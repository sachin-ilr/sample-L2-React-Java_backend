package com.example.sample.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "subject")
public class Subject extends BaseEntity {
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Staff name is mandatory")
    @Column(name = "staffname")
    private String staffName;

    @Column(name = "subjectcode")
    private String subjectCode;

    @ManyToMany
    @JoinTable(
        name = "studentsubject",
        joinColumns = @JoinColumn(name = "subjectid"),
        inverseJoinColumns = @JoinColumn(name = "studentid")
    )
    private Set<Students> students = new HashSet<>();
}