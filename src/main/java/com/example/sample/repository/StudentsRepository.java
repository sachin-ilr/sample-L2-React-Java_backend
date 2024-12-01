package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample.entity.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {
}