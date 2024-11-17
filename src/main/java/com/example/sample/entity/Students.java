package com.example.sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//import org.apache.commons.math3.analysis.function.Identity;

@Entity
@Data
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "firstname is mandatory.")
    @Column(columnDefinition = "TEXT", name = "firstname")
    private String firstname;

    @NotBlank(message = "lastname is mandatory.")
    @Column(columnDefinition = "TEXT", name = "lastname")
    private String lastname;

    @NotBlank(message = "mobileno is mandatory.")
    @Column(columnDefinition = "TEXT", name = "mobileno")
    private String mobileno;

    // @NotBlank(message = "roleno is mandatory.")
    @Column(columnDefinition = "TEXT", name = "roleno")
    private String roleno;

    // @NotBlank(message = "classname is mandatory.")
    @Column(columnDefinition = "TEXT", name = "classname")
    private String classname;

    @Column(columnDefinition = "TEXT", name = "address")
    private String address;

}