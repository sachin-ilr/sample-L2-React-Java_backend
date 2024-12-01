package com.example.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T, D, ID> {
    D save(D dto);
    Optional<D> findById(ID id);
    List<D> findAll();
    Page<D> findAll(Pageable pageable);
    void deleteById(ID id);
    D update(ID id, D dto);
}