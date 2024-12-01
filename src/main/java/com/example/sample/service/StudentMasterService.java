package com.example.sample.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.sample.dto.StudentMasterDTO;
import com.example.sample.entity.StudentMaster;
import com.example.sample.exception.ResourceNotFoundException;
import com.example.sample.repository.StudentMasterRepository;

@Service
public class StudentMasterService implements BaseService<StudentMaster, StudentMasterDTO, Integer> {

    private final StudentMasterRepository studentMasterRepository;

    public StudentMasterService(StudentMasterRepository studentMasterRepository) {
        this.studentMasterRepository = studentMasterRepository;
    }

    @Override
    public StudentMasterDTO save(StudentMasterDTO studentMasterDTO) {
        StudentMaster studentMaster = convertToEntity(studentMasterDTO);
        studentMaster = studentMasterRepository.save(studentMaster);
        return convertToDTO(studentMaster);
    }

    @Override
    public Optional<StudentMasterDTO> findById(Integer id) {
        return studentMasterRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<StudentMasterDTO> findAll() {
        return studentMasterRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<StudentMasterDTO> findAll(Pageable pageable) {
        return studentMasterRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Override
    public void deleteById(Integer id) {
        if (!studentMasterRepository.existsById(id)) {
            throw new ResourceNotFoundException("StudentMaster not found with id: " + id);
        }
        studentMasterRepository.deleteById(id);
    }

    @Override
    public StudentMasterDTO update(Integer id, StudentMasterDTO studentMasterDTO) {
        StudentMaster studentMaster = studentMasterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudentMaster not found with id: " + id));
        updateStudentMasterFromDTO(studentMaster, studentMasterDTO);
        studentMaster = studentMasterRepository.save(studentMaster);
        return convertToDTO(studentMaster);
    }

    private StudentMasterDTO convertToDTO(StudentMaster studentMaster) {
        StudentMasterDTO dto = new StudentMasterDTO();
        dto.setId(studentMaster.getId());
        dto.setFirstname(studentMaster.getFirstName());
        dto.setLastname(studentMaster.getLastName());
        dto.setRoleno(studentMaster.getRoleNo());
        dto.setSubjectname(studentMaster.getSubjectName());
        dto.setStaffname(studentMaster.getStaffName());
        dto.setSubjectcode(studentMaster.getSubjectCode());
        return dto;
    }

    private StudentMaster convertToEntity(StudentMasterDTO dto) {
        StudentMaster studentMaster = new StudentMaster();
        studentMaster.setFirstName(dto.getFirstname());
        studentMaster.setLastName(dto.getLastname());
        studentMaster.setRoleNo(dto.getRoleno());
        studentMaster.setSubjectName(dto.getSubjectname());
        studentMaster.setStaffName(dto.getStaffname());
        studentMaster.setSubjectCode(dto.getSubjectcode());
        return studentMaster;
    }

    private void updateStudentMasterFromDTO(StudentMaster studentMaster, StudentMasterDTO dto) {
        studentMaster.setFirstName(dto.getFirstname());
        studentMaster.setLastName(dto.getLastname());
        studentMaster.setRoleNo(dto.getRoleno());
        studentMaster.setSubjectName(dto.getSubjectname());
        studentMaster.setStaffName(dto.getStaffname());
        studentMaster.setSubjectCode(dto.getSubjectcode());
    }
}