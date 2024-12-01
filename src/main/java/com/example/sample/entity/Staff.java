package com.example.sample.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "staff")
public class Staff extends BaseEntity {
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Mobile number is mandatory")
    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "address")
    private String address;

    @NotBlank(message = "Subject expertise is mandatory")
    @Column(name = "subject_expert")
    private String subjectExpert;
}