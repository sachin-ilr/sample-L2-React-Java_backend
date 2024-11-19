package com.example.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sample.entity.StudentMaster;
import com.example.sample.services.StudentMasterService;

import java.util.List;

@RestController
@RequestMapping("/student-master")
public class StudentMasterController {

    @Autowired
    private StudentMasterService studentMasterService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentMaster>> getAll() {
        return studentMasterService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentMaster> getById(@PathVariable Integer id) {
        return studentMasterService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody StudentMaster studentMaster) {
        return studentMasterService.addStudentMaster(studentMaster);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody StudentMaster updatedStudent) {
        return studentMasterService.updateStudentMaster(id, updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return studentMasterService.deleteStudentMaster(id);
    }

    @PostMapping("/addFromStudent/{studentId}")
    public ResponseEntity<String> addFromStudent(@PathVariable Integer studentId) {
        return studentMasterService.addStudentMasterFromStudent(studentId);
    }
}