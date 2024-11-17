package com.example.sample.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.sample.entity.Subject;
import com.example.sample.repository.SubjectRepository;

@Service
public class SubjectServices {

    @Autowired
    private SubjectRepository subjectRepo;

    public ResponseEntity<Page<Subject>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
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

    public ResponseEntity<Subject> getById(Integer id) {
        Optional<Subject> subject = subjectRepo.findById(id);
        if (subject.isPresent()) {
            return ResponseEntity.ok(subject.get());
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404
        }
    }

    public ResponseEntity<String> updateSubject(Integer id, Subject updatedSubject) {
        if (!subjectRepo.existsById(id)) {
            return ResponseEntity.notFound().build(); // HTTP 404
        }

        updatedSubject.setId(id);
        subjectRepo.save(updatedSubject);
        return ResponseEntity.ok("Subject updated successfully");
    }

    public ResponseEntity<String> deleteSubject(Integer id) {
        if (!subjectRepo.existsById(id)) {
            return ResponseEntity.notFound().build(); // HTTP 404
        }

        subjectRepo.deleteById(id);
        return ResponseEntity.ok("Subject deleted successfully");

    }
}