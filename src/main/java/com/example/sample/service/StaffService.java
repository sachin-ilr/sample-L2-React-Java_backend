package com.example.sample.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.sample.dto.StaffDTO;
import com.example.sample.entity.Staff;
import com.example.sample.exception.ResourceNotFoundException;
import com.example.sample.repository.StaffRepository;

@Service
public class StaffService implements BaseService<Staff, StaffDTO, Integer> {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffDTO save(StaffDTO staffDTO) {
        Staff staff = convertToEntity(staffDTO);
        staff = staffRepository.save(staff);
        return convertToDTO(staff);
    }

    @Override
    public Optional<StaffDTO> findById(Integer id) {
        return staffRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<StaffDTO> findAll() {
        return staffRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<StaffDTO> findAll(Pageable pageable) {
        return staffRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Override
    public void deleteById(Integer id) {
        if (!staffRepository.existsById(id)) {
            throw new ResourceNotFoundException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }

    @Override
    public StaffDTO update(Integer id, StaffDTO staffDTO) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        updateStaffFromDTO(staff, staffDTO);
        staff = staffRepository.save(staff);
        return convertToDTO(staff);
    }

    private StaffDTO convertToDTO(Staff staff) {
        StaffDTO dto = new StaffDTO();
        dto.setId(staff.getId());
        dto.setName(staff.getName());
        dto.setMobileNo(staff.getMobileNo());
        dto.setAddress(staff.getAddress());
        dto.setSubjectExpert(staff.getSubjectExpert());
        return dto;
    }
    
    private Staff convertToEntity(StaffDTO dto) {
        Staff staff = new Staff();
        staff.setName(dto.getName());
        staff.setMobileNo(dto.getMobileNo());
        staff.setAddress(dto.getAddress());
        staff.setSubjectExpert(dto.getSubjectExpert());
        return staff;
    }

    private void updateStaffFromDTO(Staff staff, StaffDTO dto) {
        staff.setName(dto.getName());
        staff.setMobileNo(dto.getMobileNo());
        staff.setAddress(dto.getAddress());
        staff.setSubjectExpert(dto.getSubjectExpert());
    }
}