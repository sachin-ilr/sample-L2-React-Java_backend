package com.example.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}