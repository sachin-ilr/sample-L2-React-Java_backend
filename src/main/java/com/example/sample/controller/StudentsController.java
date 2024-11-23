package com.example.sample.controller;

import com.example.sample.dto.StudentsDTO;
import com.example.sample.entity.Students;
import com.example.sample.services.StudentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/students")
@Validated
public class StudentsController {

    @Autowired
    private StudentsServices studentsServ;

    @GetMapping("/all")
    public ResponseEntity<Page<Students>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return studentsServ.getAll(page, size);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@Valid @RequestBody StudentsDTO studentsDto) {
        return studentsServ.addStudent(studentsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Students> getById(@PathVariable Integer id) {
        return studentsServ.getById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(
            @PathVariable Integer id, @Valid @RequestBody StudentsDTO studentsDto) {
        return studentsServ.updateStudent(id, studentsDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        return studentsServ.deleteStudent(id);
    }
}
