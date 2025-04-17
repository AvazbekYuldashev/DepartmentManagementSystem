package com.example.department_management_system.service;


import com.example.department_management_system.dto.department.DepartmentCreateDTO;
import com.example.department_management_system.dto.department.DepartmentDTO;
import com.example.department_management_system.dto.department.DepartmentFilterDTO;
import com.example.department_management_system.dto.department.DepartmentUpdateDTO;
import com.example.department_management_system.entity.DepartmentEntity;
import com.example.department_management_system.enums.DepartmentStatus;
import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.exp.AppBadRequestExeption;
import com.example.department_management_system.mapper.DepartmentMapper;
import com.example.department_management_system.repository.department.DepartmentCustomRepository;
import com.example.department_management_system.repository.department.DepartmentRepository;
import com.example.department_management_system.util.CheckAccesss;
import com.example.department_management_system.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class DepartmentService {
    @Autowired
    @Lazy
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentCustomRepository departmentCustomRepository;
    @Autowired
    private CheckAccesss checkAccesss;

    ///  Create Department
    @Transactional
    public DepartmentDTO createDepartment(DepartmentCreateDTO request) {
        checkSuperAdminAccess();
        DepartmentDTO dto = new DepartmentDTO();
        dto.setStatus(DepartmentStatus.ACTIVE);
        dto.setTitle(request.getTitle());
        dto.setDescription(request.getDescription());
        dto.setCreatedDate(LocalDateTime.now());
        dto.setUpdatedDate(LocalDateTime.now());
        dto.setAddress(request.getAddress());
        dto.setPhoneNumber(request.getPhoneNumber());
        dto.setHeadOfDepartment(request.getHeadOfDepartment());
        dto.setType(request.getType());
        dto.setVisible(true);
        DepartmentEntity e = toEntity(dto);
        return toDto(departmentRepository.save(e));
    }

    /// Get All
    public List<DepartmentMapper> getAll() {
        return departmentRepository.findAllMapper();
    }

    /// Get By Id
    public DepartmentMapper getById(Integer id) {
        if (SpringSecurityUtil.getCurrentDepartmentId() != id) {
            checkSuperAdminAccess();
        }
        return departmentRepository.getByIdMapper(id)
                .orElseThrow(() -> new AppBadRequestExeption("Department not found with id: " + id));
    }

    /// Update status
    @Transactional
    public Boolean updateStatus(Integer id, DepartmentUpdateDTO dto) {
        if (SpringSecurityUtil.getCurrentDepartmentId() != id) {
            checkSuperAdminAccess();
        }
        return departmentRepository.updateStatus(id, dto.getStatus(), LocalDateTime.now()) > 0;
    }

    /// Update
    @Transactional
    public Boolean update(Integer id, DepartmentUpdateDTO dto) {
        if (SpringSecurityUtil.getCurrentDepartmentId() != id) {
            checkSuperAdminAccess();
        }
        int effectedRow = departmentRepository.updateDepartment(id,
                dto.getStatus(), dto.getTitle(),
                dto.getDescription(), dto.getAddress(),
                dto.getPhoneNumber(), dto.getHeadOfDepartment(),
                dto.getType(), LocalDateTime.now());
        return effectedRow > 0;
    }

    /// Delete by id for visible
    @Transactional
    public Boolean deleteWipe(Integer id, Boolean visible) {
        if (SpringSecurityUtil.getCurrentDepartmentId() != id) {
            checkSuperAdminAccess();
        }
        DepartmentEntity entity = getByIdEntity(id);
        if (entity == null) {
            throw new AppBadRequestExeption("Department not found with id: " + id);
        }
        int effectedRow = departmentRepository.updateVisible(id, visible, LocalDateTime.now());
        return effectedRow > 0;
    }
    /// Delete by id
    @Transactional
    public Boolean deleteById(Integer id) {
        checkSuperAdminAccess();
        DepartmentEntity entity = getByIdEntity(id);
        if (entity == null) {
            throw new AppBadRequestExeption("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
        return true;
    }
    ///  Get all pagination
    public PageImpl<DepartmentMapper> pagination(int page, int size){
        checkAccesss.checkAdminAccess();
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DepartmentMapper> pageObj = departmentRepository.findAllPageble(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        long total = pageObj.getTotalElements();
        return new PageImpl<DepartmentMapper>(pageObj.getContent(), pageable, total);
    }
    ///  Filter by dto for pagination
    public PageImpl<DepartmentDTO> filter(DepartmentFilterDTO dto, int page, int size) {
        checkAccesss.checkAdminAccess();
        PageImpl<DepartmentEntity> result = departmentCustomRepository.filter(dto, page, size);
        List<DepartmentDTO> dtoResult = new LinkedList<>();
        for (DepartmentEntity entity : result){dtoResult.add(toDto(entity));}
        return new PageImpl<>(dtoResult, PageRequest.of(page, size), result.getTotalElements());
    }

    /// Get By id returned entity
    public DepartmentEntity getByIdEntity(Integer id) {
        Optional<DepartmentEntity> department = departmentRepository.findByIdCustom(id);
        if (department.isEmpty()){
            throw new AppBadRequestExeption("Department not found with id: " + id);
        }
        return department.get();
    }
    /// To entity
    public DepartmentEntity toEntity(DepartmentDTO dto) {
        if (dto == null) {return null;}
        DepartmentEntity entity = new DepartmentEntity();
        entity.setStatus(dto.getStatus());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setHeadOfDepartment(dto.getHeadOfDepartment());
        entity.setType(dto.getType());
        entity.setVisible(dto.getVisible());
        return entity;
    }
    /// To DTO
    public DepartmentDTO toDto(DepartmentEntity entity) {
        if (entity == null) {return null;}
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(entity.getId());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setStatus(entity.getStatus());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setAddress(entity.getAddress());
        dto.setHeadOfDepartment(entity.getHeadOfDepartment());
        dto.setType(entity.getType());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    private void checkSuperAdminAccess() {
        if (!SpringSecurityUtil.getCurrentEmployeeRole().equals(EmployeeRole.SUPERADMIN.toString() )) {
            throw new AppBadRequestExeption("It does not belong to the current profile.");
        }
    }

}
