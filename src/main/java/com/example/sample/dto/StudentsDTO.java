package com.example.sample.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentsDTO {
    private Integer id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Mobile number is mandatory")
    private String mobileNo;

    private String roleNo;

    private String className;

    private String address;
}