package com.example.sample.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name is mandatory.")
    @Column(columnDefinition = "TEXT", name = "name")
    private String name;

    @NotBlank(message = "mobileno is mandatory.")
    @Column(columnDefinition = "TEXT", name = "mobileno")
    private String mobileno;

    @Column(columnDefinition = "TEXT", name = "address")
    private String address;

    @NotBlank(message = "subjectexpert is mandatory.")
    @Column(columnDefinition = "TEXT", name = "subjectexpert")
    private String subjectexpert;

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