package com.example.sample.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentsDTO {
    private Integer id;

    @NotBlank(message = "First name is mandatory")
    private String firstname;

    @NotBlank(message = "Last name is mandatory")
    private String lastname;

    @NotBlank(message = "Mobile number is mandatory")
    private String mobileno;

    private String classname;
    private String address;
}
