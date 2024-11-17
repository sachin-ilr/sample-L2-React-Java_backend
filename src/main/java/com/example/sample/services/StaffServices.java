package com.example.sample.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.sample.entity.Staff;
import com.example.sample.repository.StaffRepository;

@Service
public class StaffServices {

    @Autowired
    private StaffRepository staffRepo;

    public ResponseEntity<Page<Staff>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Staff> staffPage = staffRepo.findAll(pageable);
        return ResponseEntity.ok(staffPage);
    }

    public ResponseEntity<String> staffAdd(Staff staff) {
        try {
            staffRepo.save(staff);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added staff");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add staff");
        }
    }

    public ResponseEntity<Staff> getById(Integer id) {
        Optional<Staff> staff = staffRepo.findById(id);
        if (staff.isPresent()) {
            return ResponseEntity.ok(staff.get());
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404
        }
    }

    public ResponseEntity<String> updateStaff(Integer id, Staff updatedStaff) {
        if (!staffRepo.existsById(id)) {
            return ResponseEntity.notFound().build(); // HTTP 404
        }

        updatedStaff.setId(id); // Ensure the ID is set for the update
        staffRepo.save(updatedStaff); // Save the updated staff
        return ResponseEntity.ok("Staff updated successfully");
    }

    public ResponseEntity<String> deleteStaff(Integer id) {
        if (!staffRepo.existsById(id)) {
            return ResponseEntity.notFound().build(); // HTTP 404
        }

        staffRepo.deleteById(id);
        return ResponseEntity.ok("Staff deleted successfully");
    }
}