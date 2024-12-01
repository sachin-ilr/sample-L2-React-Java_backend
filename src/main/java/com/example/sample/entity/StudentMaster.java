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
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role_no")
    private String roleNo;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "staff_name")
    private String staffName;

    @Column(name = "subject_code")
    private String subjectCode;
}