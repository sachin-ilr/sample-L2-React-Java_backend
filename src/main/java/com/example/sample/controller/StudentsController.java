package com.example.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.sample.entity.Students;
import com.example.sample.services.StudentsServices;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsServices studentsServ;

    @GetMapping("/all")
    public ResponseEntity<Page<Students>> selectAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return studentsServ.getAll(page, size);
    }

    @GetMapping("/all")
    public ResponseEntity<?> selectAll() {
        return studentsServ.selectAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> studentsAdd(@RequestBody Students students) {
        return studentsServ.studentsAdd(students);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> studentsDelete(@PathVariable("id") Integer id) {
        return studentsServ.deleteStudent(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody Students student) {
        return studentsServ.updateStudent(id, student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Students> getStudentById(@PathVariable Integer id) {
        return studentsServ.getStudentById(id);
    }
}