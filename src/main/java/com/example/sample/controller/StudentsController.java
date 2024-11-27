package com.example.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.example.sample.entity.Students;
import com.example.sample.services.StudentsServices;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    @Autowired
    private StudentsServices studentsServ;

    // Get all students with pagination
    @GetMapping
    public ResponseEntity<Page<Students>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        Page<Students> students = studentsServ.getAll(page, size);
        return ResponseEntity.ok(students);
    }

    // Get a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Students> getById(@PathVariable Integer id) {
        return studentsServ.getById(id);
    }

    // Get all students (no pagination)
    @GetMapping("/all")
    public ResponseEntity<List<Students>> getAllStudents() {
        return studentsServ.getAllStudents();
    }

    // Add a new student
    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody StudentsDTO studentsDTO) {
        return studentsServ.addStudent(studentsDTO);
    }

    // Update a student
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentsDTO studentsDTO) {
        return studentsServ.updateStudent(id, studentsDTO);
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        return studentsServ.deleteStudent(id);
    }
}
