package com.example.sample.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sample.entity.StudentMaster;
import com.example.sample.entity.Students;
import com.example.sample.repository.StudentMasterRepository;
import com.example.sample.repository.StudentsRepository;

@Service
public class StudentMasterService {

    @Autowired
    private StudentMasterRepository studentMasterRepo;

    @Autowired
    private StudentsRepository studentsRepo;

    public ResponseEntity<String> addOrUpdateStudentMaster(Students student) {
        try {
            StudentMaster studentMaster = new StudentMaster();
            studentMaster.setFirstname(student.getFirstname());
            studentMaster.setLastname(student.getLastname());
            studentMaster.setRoleno(student.getRoleno());
            studentMasterRepo.save(studentMaster);
            return ResponseEntity.ok("Student Master saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save Student Master");
        }
    }

    public ResponseEntity<List<StudentMaster>> getAll() {
        List<StudentMaster> studentMasters = studentMasterRepo.findAll();
        return ResponseEntity.ok(studentMasters);
    }

    public ResponseEntity<StudentMaster> getById(Integer id) {
        Optional<StudentMaster> studentMaster = studentMasterRepo.findById(id);
        return studentMaster.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<String> deleteById(Integer id) {
        if (!studentMasterRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Master not found");
        }

        studentMasterRepo.deleteById(id);
        return ResponseEntity.ok("Student Master deleted successfully");
    }
}
