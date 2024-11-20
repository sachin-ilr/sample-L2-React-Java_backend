package com.example.sample.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @Column(columnDefinition = "TEXT", name = "roleno", unique = true)
    private String roleno;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

    @Column(columnDefinition = "TEXT", name = "classname")
    private String classname;

    @Column(columnDefinition = "TEXT", name = "address")
    private String address;

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

    public String generateRoleNo() {
        return String.format("R2024%04d", id);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.getStudents().add(this);
    }
}

// DTO
