package com.example.sample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.sample.entity.StudentMaster;
import com.example.sample.entity.Students;
import com.example.sample.entity.Subject;
import com.example.sample.repository.StudentMasterRepository;
import com.example.sample.repository.StudentsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentMasterService {

    @Autowired
    private StudentMasterRepository studentMasterRepo;

    @Autowired
    private StudentsRepository studentsRepo;

    public void createOrUpdateStudentMaster(Students student) {
        StudentMaster studentMaster = new StudentMaster();
        studentMaster.setFirstname(student.getFirstname());
        studentMaster.setLastname(student.getLastname());
        studentMaster.setRoleno(student.getRoleno());

        Optional<StudentMaster> existingRecord = studentMasterRepo.findById(student.getId());
        if (existingRecord.isPresent()) {
            studentMaster.setId(existingRecord.get().getId());
        }

        studentMasterRepo.save(studentMaster);
    }

    public ResponseEntity<String> addSubjectForStudent(Integer studentId, Subject subject) {
        Optional<Students> studentOpt = studentsRepo.findById(studentId);
        if (!studentOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Students student = studentOpt.get();
        createOrUpdateStudentMaster(student);

        return ResponseEntity.ok("Subject added and Student Master updated successfully");
    }

    public ResponseEntity<String> addStudentMasterFromStudent(Integer studentId) {
        Optional<Students> studentOpt = studentsRepo.findById(studentId);
        if (!studentOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Students student = studentOpt.get();
        createOrUpdateStudentMaster(student);

        return ResponseEntity.status(201).body("Successfully added or updated student master");
    }

    public ResponseEntity<List<StudentMaster>> getAll() {
        List<StudentMaster> studentMasters = studentMasterRepo.findAll();
        return ResponseEntity.ok(studentMasters);
    }

    public ResponseEntity<StudentMaster> getById(Integer id) {
        Optional<StudentMaster> studentMaster = studentMasterRepo.findById(id);
        return studentMaster.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> addStudentMaster(StudentMaster studentMaster) {
        studentMasterRepo.save(studentMaster);
        return ResponseEntity.status(201).body("Successfully added student master");
    }

    public ResponseEntity<String> updateStudentMaster(Integer id, StudentMaster updatedStudentMaster) {
        if (!studentMasterRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        updatedStudentMaster.setId(id);
        studentMasterRepo.save(updatedStudentMaster);
        return ResponseEntity.ok("Successfully updated student master");
    }

    public ResponseEntity<String> deleteStudentMaster(Integer id) {
        if (!studentMasterRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        studentMasterRepo.deleteById(id);
        return ResponseEntity.ok("Successfully deleted student master");
    }
}