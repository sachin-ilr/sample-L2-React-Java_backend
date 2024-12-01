package com.example.sample.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.sample.dto.SubjectDTO;
import com.example.sample.entity.Subject;
import com.example.sample.exception.ResourceNotFoundException;
import com.example.sample.repository.SubjectRepository;

@Service
public class SubjectService implements BaseService<Subject, SubjectDTO, Integer> {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubjectDTO save(SubjectDTO subjectDTO) {
        Subject subject = convertToEntity(subjectDTO);
        subject = subjectRepository.save(subject);
        return convertToDTO(subject);
    }

    @Override
    public Optional<SubjectDTO> findById(Integer id) {
        return subjectRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SubjectDTO> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Override
    public void deleteById(Integer id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDTO update(Integer id, SubjectDTO subjectDTO) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        updateSubjectFromDTO(subject, subjectDTO);
        subject = subjectRepository.save(subject);
        return convertToDTO(subject);
    }

    private SubjectDTO convertToDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setName(subject.getStaffName());
        dto.setSubjectcode(subject.getSubjectCode());
        return dto;
    }

    private Subject convertToEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setStaffName(dto.getName());
        subject.setSubjectCode(dto.getSubjectcode());
        return subject;
    }

    private void updateSubjectFromDTO(Subject subject, SubjectDTO dto) {
        subject.setName(dto.getName());
        subject.setStaffName(dto.getName());
        subject.setSubjectCode(dto.getSubjectcode());
    }
}