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

import com.example.sample.dto.StudentsDTO;
import com.example.sample.entity.Students;
import com.example.sample.repository.StudentMasterRepository;
import com.example.sample.repository.StudentsRepository;

@Service
public class StudentsServices {

    @Autowired
    private StudentsRepository studentsRepo;

    @Autowired
    private StudentMasterRepository studentMasterRepo;

    public ResponseEntity<Page<Students>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Students> studentsPage = studentsRepo.findAll(pageable);
        return ResponseEntity.ok(studentsPage);
    }

    public ResponseEntity<String> addStudent(StudentsDTO studentsDto) {
        try {
            Students student = mapToEntity(studentsDto);
            studentsRepo.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added student");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add student");
        }
    }

    public ResponseEntity<String> updateStudent(Integer id, StudentsDTO studentsDto) {
        if (!studentsRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        Students updatedStudent = mapToEntity(studentsDto);
        updatedStudent.setId(id);
        studentsRepo.save(updatedStudent);
        return ResponseEntity.ok("Successfully updated student");
    }

    public ResponseEntity<Students> getById(Integer id) {
        Optional<Students> student = studentsRepo.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<String> deleteStudent(Integer id) {
        if (!studentsRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        studentsRepo.deleteById(id);
        return ResponseEntity.ok("Successfully deleted student");
    }

    public ResponseEntity<List<Students>> getAllStudents() {
        List<Students> students = studentsRepo.findAll();
        return ResponseEntity.ok(students);
    }

    private Students mapToEntity(StudentsDTO studentsDto) {
        Students student = new Students();
        student.setFirstname(studentsDto.getFirstname());
        student.setLastname(studentsDto.getLastname());
        student.setMobileno(studentsDto.getMobileno());
        student.setClassname(studentsDto.getClassname());
        student.setAddress(studentsDto.getAddress());
        return student;
    }
}
