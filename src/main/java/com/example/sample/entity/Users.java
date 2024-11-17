package com.example.sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

//import org.apache.commons.math3.analysis.function.Identity;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "username is mandatory.")
    @Column(columnDefinition = "TEXT", name = "username")
    private String username;

    @Column(columnDefinition = "TEXT", name = "address")
    private String address;

    @NotBlank(message = "date is mandatory.")
    @Column(columnDefinition = "TEXT", name = "date")
    private String date;

    // @NotBlank(message = "number is mandatory.")
    @Column(columnDefinition = "TEXT", name = "number")
    private int number;

}