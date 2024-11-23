package com.example.sample.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sample.entity.StudentMaster;
import com.example.sample.repository.StudentMasterRepository;

@Service
public class StudentMasterService {

    @Autowired
    private StudentMasterRepository studentMasterRepo;

    public ResponseEntity<String> addStudentMaster(StudentMaster studentMaster) {
        try {
            studentMasterRepo.save(studentMaster);
            return ResponseEntity.ok("Student Master added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to add Student Master");
        }
    }

    public ResponseEntity<String> updateStudentMaster(Integer id, StudentMaster updatedStudentMaster) {
        if (!studentMasterRepo.existsById(id)) {
            return ResponseEntity.status(404).body("Student Master not found");
        }

        updatedStudentMaster.setId(id);
        studentMasterRepo.save(updatedStudentMaster);
        return ResponseEntity.ok("Student Master updated successfully");
    }

    public ResponseEntity<String> deleteStudentMaster(Integer id) {
        if (!studentMasterRepo.existsById(id)) {
            return ResponseEntity.status(404).body("Student Master not found");
        }

        studentMasterRepo.deleteById(id);
        return ResponseEntity.ok("Student Master deleted successfully");
    }

    public ResponseEntity<String> addStudentMasterFromStudent(Integer studentId) {
        // Assuming logic to create StudentMaster from Students entity
        // Logic to fetch Student by ID and create StudentMaster
        return ResponseEntity.status(201).body("Student Master created from Student");
    }

    public ResponseEntity<List<StudentMaster>> getAll() {
        List<StudentMaster> studentMasters = studentMasterRepo.findAll();
        return ResponseEntity.ok(studentMasters);
    }

    public ResponseEntity<StudentMaster> getById(Integer id) {
        Optional<StudentMaster> studentMaster = studentMasterRepo.findById(id);
        return studentMaster.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }
}
