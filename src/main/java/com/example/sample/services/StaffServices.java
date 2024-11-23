package com.example.sample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sample.dto.StaffDTO;
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

    public ResponseEntity<String> addStaff(StaffDTO staffDto) {
        Staff staff = mapToEntity(staffDto);
        staffRepo.save(staff);
        return ResponseEntity.status(HttpStatus.CREATED).body("Staff added successfully");
    }

    public ResponseEntity<Staff> getById(Integer id) {
        return staffRepo.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<String> updateStaff(Integer id, StaffDTO staffDto) {
        if (!staffRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Staff not found");
        }
        Staff updatedStaff = mapToEntity(staffDto);
        updatedStaff.setId(id);
        staffRepo.save(updatedStaff);
        return ResponseEntity.ok("Staff updated successfully");
    }

    public ResponseEntity<String> deleteStaff(Integer id) {
        if (!staffRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Staff not found");
        }
        staffRepo.deleteById(id);
        return ResponseEntity.ok("Staff deleted successfully");
    }

    private Staff mapToEntity(StaffDTO staffDto) {
        Staff staff = new Staff();
        staff.setName(staffDto.getName());
        staff.setMobileno(staffDto.getMobileno());
        staff.setAddress(staffDto.getAddress());
        staff.setSubjectexpert(staffDto.getSubjectexpert());
        return staff;
    }
}
