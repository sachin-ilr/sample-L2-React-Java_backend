package com.example.sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "student_master")
public class StudentMaster extends BaseEntity {
    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "roleno")
    private String roleNo;

    @Column(name = "subjectname")
    private String subjectName;

    @Column(name = "staffname")
    private String staffName;

    @Column(name = "subjectcode")
    private String subjectCode;
}