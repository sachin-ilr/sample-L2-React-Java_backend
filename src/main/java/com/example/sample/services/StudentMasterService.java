package com.example.sample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<String> createOrUpdateStudentMaster(Students student) {
        try {
            StudentMaster studentMaster = new StudentMaster();
            studentMaster.setFirstname(student.getFirstname());
            studentMaster.setLastname(student.getLastname());
            studentMaster.setRoleno(student.getRoleno());
            studentMaster.setSubjectname("Default Subject");
            studentMaster.setStaffname("Default Staff");

            // Handle existing student mapping to avoid duplicates
            Optional<StudentMaster> existing = studentMasterRepo.findById(student.getId());
            existing.ifPresent(sm -> studentMaster.setId(sm.getId()));

            studentMasterRepo.save(studentMaster);
            return ResponseEntity.ok("Student Master saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save Student Master");
        }
    }

    public ResponseEntity<String> addSubjectForStudent(Integer studentId, Subject subject) {
        Optional<Students> studentOpt = studentsRepo.findById(studentId);
        if (studentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        Students student = studentOpt.get();
        createOrUpdateStudentMaster(student);

        return ResponseEntity.ok("Subject added and Student Master updated successfully");
    }

    public ResponseEntity<String> addStudentMasterFromStudent(Integer studentId) {
        Optional<Students> studentOpt = studentsRepo.findById(studentId);
        if (studentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        Students student = studentOpt.get();
        createOrUpdateStudentMaster(student);

        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added or updated Student Master");
    }

    public ResponseEntity<List<StudentMaster>> getAll() {
        List<StudentMaster> studentMasters = studentMasterRepo.findAll();
        return ResponseEntity.ok(studentMasters);
    }

    public ResponseEntity<StudentMaster> getById(Integer id) {
        Optional<StudentMaster> studentMaster = studentMasterRepo.findById(id);
        return studentMaster.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<String> addStudentMaster(StudentMaster studentMaster) {
        studentMasterRepo.save(studentMaster);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added Student Master");
    }

    public ResponseEntity<String> updateStudentMaster(Integer id, StudentMaster updatedStudentMaster) {
        if (!studentMasterRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Master not found");
        }

        updatedStudentMaster.setId(id);
        studentMasterRepo.save(updatedStudentMaster);
        return ResponseEntity.ok("Successfully updated Student Master");
    }

    public ResponseEntity<String> deleteStudentMaster(Integer id) {
        if (!studentMasterRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Master not found");
        }

        studentMasterRepo.deleteById(id);
        return ResponseEntity.ok("Successfully deleted Student Master");
    }
}
