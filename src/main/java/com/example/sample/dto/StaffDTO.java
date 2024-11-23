package com.example.sample.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StaffDTO {
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Mobile number is mandatory")
    private String mobileno;

    private String address;

    @NotBlank(message = "Subject expertise is mandatory")
    private String subjectexpert;
}
