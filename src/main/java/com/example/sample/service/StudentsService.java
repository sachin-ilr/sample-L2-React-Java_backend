package com.example.sample.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.sample.dto.StudentsDTO;
import com.example.sample.entity.Students;
import com.example.sample.exception.ResourceNotFoundException;
import com.example.sample.repository.StudentsRepository;

@Service
public class StudentsService implements BaseService<Students, StudentsDTO, Integer> {

    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public StudentsDTO save(StudentsDTO studentsDTO) {
        Students students = convertToEntity(studentsDTO);
        students = studentsRepository.save(students);
        return convertToDTO(students);
    }

    @Override
    public Optional<StudentsDTO> findById(Integer id) {
        return studentsRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<StudentsDTO> findAll() {
        return studentsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<StudentsDTO> findAll(Pageable pageable) {
        return studentsRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Override
    public void deleteById(Integer id) {
        if (!studentsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentsRepository.deleteById(id);
    }

    @Override
    public StudentsDTO update(Integer id, StudentsDTO studentsDTO) {
        Students students = studentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        updateStudentsFromDTO(students, studentsDTO);
        students = studentsRepository.save(students);
        return convertToDTO(students);
    }

    private StudentsDTO convertToDTO(Students students) {
        StudentsDTO dto = new StudentsDTO();
        dto.setId(students.getId());
        dto.setFirstName(students.getFirstName());
        dto.setLastName(students.getLastName());
        dto.setMobileNo(students.getMobileNo());
        dto.setRoleNo(students.getRoleNo());
        dto.setClassName(students.getClassName());
        dto.setAddress(students.getAddress());
        return dto;
    }
    
    private Students convertToEntity(StudentsDTO dto) {
        Students students = new Students();
        students.setFirstName(dto.getFirstName());
        students.setLastName(dto.getLastName());
        students.setMobileNo(dto.getMobileNo());
        students.setRoleNo(dto.getRoleNo());
        students.setClassName(dto.getClassName());
        students.setAddress(dto.getAddress());
        return students;
    }

    private void updateStudentsFromDTO(Students students, StudentsDTO dto) {
        students.setFirstName(dto.getFirstName());
        students.setLastName(dto.getClassName());
        students.setMobileNo(dto.getMobileNo());
        students.setRoleNo(dto.getMobileNo());
        students.setClassName(dto.getClassName());
        students.setAddress(dto.getAddress());
    }
}