package com.example.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.sample.entity.Subject;
import com.example.sample.services.SubjectServices;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectServices subjectServ;

    @GetMapping("/all")
    public ResponseEntity<Page<Subject>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return subjectServ.getAll(page, size);
    }

    @PostMapping("/add")
    public ResponseEntity<?> subjectAdd(@RequestBody Subject subject) {
        return subjectServ.subjectAdd(subject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
        return subjectServ.getById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        return subjectServ.updateSubject(id, subject);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Integer id) {
        return subjectServ.deleteSubject(id);
    }
}