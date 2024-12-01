package com.example.sample.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.dto.StudentsDTO;
import com.example.sample.service.StudentsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public ResponseEntity<Page<StudentsDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<StudentsDTO> studentsPage = studentsService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(studentsPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentsDTO> getStudentById(@PathVariable Integer id) {
        return studentsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentsDTO> createStudent(@Valid @RequestBody StudentsDTO studentsDTO) {
        StudentsDTO createdStudent = studentsService.save(studentsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentsDTO> updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentsDTO studentsDTO) {
        StudentsDTO updatedStudent = studentsService.update(id, studentsDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}