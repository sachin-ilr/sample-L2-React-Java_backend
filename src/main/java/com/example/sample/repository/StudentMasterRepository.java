package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sample.entity.StudentMaster;

@Repository
public interface StudentMasterRepository extends JpaRepository<StudentMaster, Integer> {
}