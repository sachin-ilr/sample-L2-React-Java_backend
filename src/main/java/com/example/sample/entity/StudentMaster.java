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
@Table(name = "studentMaster")
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
    private String name;

    @Column(columnDefinition = "TEXT", name = "staffname")
    private String staffname;

    @Column(columnDefinition = "TEXT", name = "subjectcode")
    private String subjectcode;

    @Column(columnDefinition = "DATE&TIME", name = "createddate")
    private String createddate;

    @Column(columnDefinition = "DATE&TIME", name = "modifieddate")
    private String modifieddate;

}