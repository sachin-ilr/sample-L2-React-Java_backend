package com.example.sample.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sample.entity.Subject;
import com.example.sample.repository.SubjectRepository;

@Service
public class SubjectServices {

    @Autowired
    private SubjectRepository subjectRepo;

    public ResponseEntity<String> subjectAdd(Subject subject) {
        try {
            subjectRepo.save(subject);
            return ResponseEntity.status(201).body("Successfully added subject");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to add subject");
        }
    }

    public ResponseEntity<String> subjectAddAll(List<Subject> subjects) {
        try {
            subjectRepo.saveAll(subjects);
            return ResponseEntity.status(201).body("Successfully added all subjects");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to add subjects");
        }
    }

    public ResponseEntity<Subject> getById(Integer id) {
        Optional<Subject> subject = subjectRepo.findById(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }

    public ResponseEntity<String> updateSubject(Integer id, Subject updatedSubject) {
        if (!subjectRepo.existsById(id)) {
            return ResponseEntity.status(404).body("Subject not found");
        }

        updatedSubject.setId(id);
        subjectRepo.save(updatedSubject);
        return ResponseEntity.ok("Subject updated successfully");
    }

    public ResponseEntity<String> deleteSubject(Integer id) {
        if (!subjectRepo.existsById(id)) {
            return ResponseEntity.status(404).body("Subject not found");
        }

        subjectRepo.deleteById(id);
        return ResponseEntity.ok("Subject deleted successfully");
    }

    public ResponseEntity<Page<Subject>> getAll(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Subject> subjectPage = subjectRepo.findAll(pageable);
    return ResponseEntity.ok(subjectPage);
}

}
