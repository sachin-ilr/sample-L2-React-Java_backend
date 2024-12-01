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

import com.example.sample.dto.StudentMasterDTO;
import com.example.sample.service.StudentMasterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student-master")
public class StudentMasterController {

    private final StudentMasterService studentMasterService;

    public StudentMasterController(StudentMasterService studentMasterService) {
        this.studentMasterService = studentMasterService;
    }

    @GetMapping
    public ResponseEntity<Page<StudentMasterDTO>> getAllStudentMasters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<StudentMasterDTO> studentMasterPage = studentMasterService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(studentMasterPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentMasterDTO> getStudentMasterById(@PathVariable Integer id) {
        return studentMasterService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentMasterDTO> createStudentMaster(@Valid @RequestBody StudentMasterDTO studentMasterDTO) {
        StudentMasterDTO createdStudentMaster = studentMasterService.save(studentMasterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentMaster);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentMasterDTO> updateStudentMaster(@PathVariable Integer id, @Valid @RequestBody StudentMasterDTO studentMasterDTO) {
        StudentMasterDTO updatedStudentMaster = studentMasterService.update(id, studentMasterDTO);
        return ResponseEntity.ok(updatedStudentMaster);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentMaster(@PathVariable Integer id) {
        studentMasterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}