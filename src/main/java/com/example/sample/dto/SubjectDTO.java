package com.example.sample.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectDTO {
    private Integer id;

    @NotBlank(message = "Subject name is mandatory")
    private String name;

    @NotBlank(message = "Staff name is mandatory")
    private String staffNnme;

    private String subjectcode;
}