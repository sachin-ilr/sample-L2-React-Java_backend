package com.example.sample.controller;

import com.example.sample.dto.StaffDTO;
import com.example.sample.entity.Staff;
import com.example.sample.services.StaffServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/staff")
@Validated
public class StaffController {

    @Autowired
    private StaffServices staffServ;

    @GetMapping("/all")
    public ResponseEntity<Page<Staff>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return staffServ.getAll(page, size);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStaff(@Valid @RequestBody StaffDTO staffDto) {
        return staffServ.addStaff(staffDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getById(@PathVariable Integer id) {
        return staffServ.getById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStaff(
            @PathVariable Integer id, @Valid @RequestBody StaffDTO staffDto) {
        return staffServ.updateStaff(id, staffDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Integer id) {
        return staffServ.deleteStaff(id);
    }
}
