package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

}