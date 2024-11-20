package com.example.sample.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sample.entity.Students;
import com.example.sample.repository.StudentsRepository;

@Service
public class StudentsServices {

    @Autowired
    private StudentsRepository studentsRepo;

    @Autowired
    private StudentMasterService studentMasterService;

    public ResponseEntity<Page<Students>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Students> studentsPage = studentsRepo.findAll(pageable);
        return ResponseEntity.ok(studentsPage);
    }

    public List<Students> getAll() {
        return studentsRepo.findAll();
    }

    public ResponseEntity<String> studentsAdd(Students students) {
        try {
            studentsRepo.save(students);
            students.setRoleno(students.generateRoleNo());
            studentsRepo.save(students);
            studentMasterService.createOrUpdateStudentMaster(students);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added student");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add student");
        }
    }

    public ResponseEntity<?> selectAll() {
        List<Students> allStudents = studentsRepo.findAll();
        if (allStudents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Record is empty");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(allStudents);
        }
    }

    public ResponseEntity<Students> getStudentById(Integer id) {
        Optional<Students> student = studentsRepo.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> updateStudent(Integer id, Students updatedStudent) {
        if (!studentsRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        updatedStudent.setId(id);
        studentsRepo.save(updatedStudent);
        return ResponseEntity.ok("Student updated successfully");
    }

    public ResponseEntity<String> deleteStudent(Integer id) {
        if (!studentsRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        studentsRepo.deleteById(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}