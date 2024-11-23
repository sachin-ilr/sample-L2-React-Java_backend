package com.example.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.entity.Staff;
import com.example.sample.services.StaffServices;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffServices staffServ;

    // Get all staff with pagination
    @GetMapping("/all")
    public ResponseEntity<Page<Staff>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return staffServ.getAll(page, size);
    }

    // Add a new staff member
    @PostMapping("/add")
    public ResponseEntity<?> staffAdd(@RequestBody Staff staff) {
        return staffServ.staffAdd(staff);
    }

    // Get a single staff member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Integer id) {
        return staffServ.getById(id);
    }

    // Update an existing staff member
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStaff(@PathVariable Integer id, @RequestBody Staff staff) {
        return staffServ.updateStaff(id, staff);
    }

    // Delete a staff member by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable("id") Integer id) {
        return staffServ.deleteStaff(id);
    }
}