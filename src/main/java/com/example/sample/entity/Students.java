package com.example.sample.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Students extends BaseEntity {
    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Mobile number is mandatory")
    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "role_no", unique = true)
    private String roleNo;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

    @Column(name = "class_name")
    private String className;

    @Column(name = "address")
    private String address;

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.getStudents().add(this);
    }

    public String generateRoleNo() {
        return String.format("R2024%04d", getId());
    }
}