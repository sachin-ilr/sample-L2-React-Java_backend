package com.example.sample.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sample.dto.StudentsDTO;
import com.example.sample.entity.Students;
import com.example.sample.repository.StudentsRepository;

@Service
public class StudentsServices {

    private static final Logger logger = LoggerFactory.getLogger(StudentsServices.class);

    @Autowired
    private StudentsRepository studentsRepo;

    // Get all students with pagination
    public Page<Students> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentsRepo.findAll(pageable);
    }

    // Get a student by ID
    public ResponseEntity<Students> getById(Integer id) {
        Optional<Students> studentOpt = studentsRepo.findById(id);
        return studentOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all students (no pagination)
    public ResponseEntity<List<Students>> getAllStudents() {
        try {
            List<Students> students = studentsRepo.findAll();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            logger.error("Error retrieving all students: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Add a new student
    public ResponseEntity<String> addStudent(StudentsDTO studentsDTO) {
        try {
            Students student = new Students();
            student.setFirstname(studentsDTO.getFirstname());
            student.setLastname(studentsDTO.getLastname());
            student.setMobileno(studentsDTO.getMobileno());
            student.setClassname(studentsDTO.getClassname());
            student.setAddress(studentsDTO.getAddress());
            studentsRepo.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
        } catch (Exception e) {
            logger.error("Error adding student: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add student");
        }
    }

    // Update a student
    public ResponseEntity<String> updateStudent(Integer id, StudentsDTO studentsDTO) {
        try {
            Optional<Students> existingStudentOpt = studentsRepo.findById(id);
            if (existingStudentOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
            Students existingStudent = existingStudentOpt.get();
            existingStudent.setFirstname(studentsDTO.getFirstname());
            existingStudent.setLastname(studentsDTO.getLastname());
            existingStudent.setMobileno(studentsDTO.getMobileno());
            existingStudent.setClassname(studentsDTO.getClassname());
            existingStudent.setAddress(studentsDTO.getAddress());
            studentsRepo.save(existingStudent);
            return ResponseEntity.ok("Student updated successfully");
        } catch (Exception e) {
            logger.error("Error updating student: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update student");
        }
    }

    // Delete a student
    public ResponseEntity<String> deleteStudent(Integer id) {
        try {
            if (!studentsRepo.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
            studentsRepo.deleteById(id);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (Exception e) {
            logger.error("Error deleting student: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete student");
        }
    }
}
