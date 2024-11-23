package com.example.sample.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sample.entity.Students;
import com.example.sample.entity.Subject;
import com.example.sample.repository.StudentsRepository;
import com.example.sample.repository.SubjectRepository;

@Service
public class SubjectServices {

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private StudentsRepository studentsRepo;

    @Autowired
    private StudentMasterService studentMasterService;

    public ResponseEntity<String> addSubjectToStudent(Integer studentId, Subject subject) {
        Optional<Students> studentOpt = studentsRepo.findById(studentId);
        if (studentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        Students student = studentOpt.get();

        // Handle relationship
        student.addSubject(subject);
        subject.getStudents().add(student);

        try {
            subjectRepo.save(subject);
            studentsRepo.save(student);
            studentMasterService.createOrUpdateStudentMaster(student);
            return ResponseEntity.ok("Successfully added subject and updated Student Master");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add subject to student");
        }
    }

    public ResponseEntity<Page<Subject>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Subject> subjectPage = subjectRepo.findAll(pageable);
        return ResponseEntity.ok(subjectPage);
    }

    public ResponseEntity<String> subjectAdd(Subject subject) {
        try {
            subjectRepo.save(subject);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added subject");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add subject");
        }
    }

    public ResponseEntity<String> subjectAddAll(List<Subject> subjects) {
        try {
            subjectRepo.saveAll(subjects);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added all subjects");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add subjects");
        }
    }

    public ResponseEntity<Subject> getById(Integer id) {
        Optional<Subject> subject = subjectRepo.findById(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<String> updateSubject(Integer id, Subject updatedSubject) {
        if (!subjectRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found");
        }

        updatedSubject.setId(id);
        subjectRepo.save(updatedSubject);
        return ResponseEntity.ok("Subject updated successfully");
    }

    public ResponseEntity<String> deleteSubject(Integer id) {
        if (!subjectRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found");
        }

        subjectRepo.deleteById(id);
        return ResponseEntity.ok("Subject deleted successfully");
    }
}
