package com.example.department_management_system.service;


import com.example.department_management_system.dto.department.DepartmentCreateDTO;
import com.example.department_management_system.dto.department.DepartmentDTO;
import com.example.department_management_system.dto.department.DepartmentFilterDTO;
import com.example.department_management_system.dto.department.DepartmentUpdateDTO;
import com.example.department_management_system.entity.DepartmentEntity;
import com.example.department_management_system.enums.AppLangulage;
import com.example.department_management_system.enums.DepartmentStatus;
import com.example.department_management_system.exp.AppBadRequestExeption;
import com.example.department_management_system.mapper.department.DepartmentMapper;
import com.example.department_management_system.mapper.department.DepartmentMapperDE;
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
    @Autowired
    private DepartmentMapperDE departmentMapper;
    @Autowired
    private ResourceBoundleService boundleService;

    ///  Create Department
    @Transactional
    public DepartmentDTO createDepartment(DepartmentCreateDTO request) {
        checkAccesss.checkSuperAdminAccess();
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
        DepartmentEntity e = departmentMapper.toEntity(dto);
        return departmentMapper.toDto(departmentRepository.save(e));
    }
    /// Get All
    public List<DepartmentMapper> getAll() {
        return departmentRepository.findAllMapper();
    }
    /// Get By Id
    public DepartmentMapper getById(Integer id, AppLangulage lang) {
        return departmentRepository.getByIdMapper(id)
                .orElseThrow(() -> new AppBadRequestExeption(boundleService.getMessage("department.not.found.with.id", lang) + id));
    }
    /// Update status
    @Transactional
    public Boolean updateStatus(Integer id, DepartmentUpdateDTO dto) {
        if (SpringSecurityUtil.getCurrentDepartmentId() != id) {
            checkAccesss.checkSuperAdminAccess();
        }
        return departmentRepository.updateStatus(id, dto.getStatus(), LocalDateTime.now()) > 0;
    }
    /// Update
    @Transactional
    public Boolean update(Integer id, DepartmentUpdateDTO dto) {
        if (!SpringSecurityUtil.getCurrentDepartmentId().equals(id)) {
            checkAccesss.checkSuperAdminAccess();
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
    public Boolean deleteWipe(Integer id, Boolean visible, AppLangulage lang) {
        if (SpringSecurityUtil.getCurrentDepartmentId() != id) {
            checkAccesss.checkSuperAdminAccess();
        }
        DepartmentEntity entity = getByIdEntity(id, lang);
        if (entity == null) {
            throw new AppBadRequestExeption(boundleService.getMessage("department.not.found.with.id", lang) + id);
        }
        int effectedRow = departmentRepository.updateVisible(id, visible, LocalDateTime.now());
        return effectedRow > 0;
    }
    /// Delete by id
    @Transactional
    public Boolean deleteById(Integer id, AppLangulage lang) {
        checkAccesss.checkSuperAdminAccess();
        DepartmentEntity entity = getByIdEntity(id, lang);
        if (entity == null) {
            throw new AppBadRequestExeption(boundleService.getMessage("department.not.found.with.id", lang) + id);
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
        return new PageImpl<>(pageObj.getContent(), pageable, total);
    }
    ///  Filter by dto for pagination
    public PageImpl<DepartmentDTO> filter(DepartmentFilterDTO dto, int page, int size) {
        checkAccesss.checkAdminAccess();
        PageImpl<DepartmentEntity> result = departmentCustomRepository.filter(dto, page, size);
        List<DepartmentDTO> dtoResult = new LinkedList<>();
        for (DepartmentEntity entity : result){dtoResult.add(departmentMapper.toDto(entity));}
        return new PageImpl<>(dtoResult, PageRequest.of(page, size), result.getTotalElements());
    }
    /// Get By id returned entity
    public DepartmentEntity getByIdEntity(Integer id, AppLangulage lang) {
        Optional<DepartmentEntity> department = departmentRepository.findByIdCustom(id);
        if (department.isEmpty()){
            throw new AppBadRequestExeption(boundleService.getMessage("department.not.found.with.id", lang) + id);
        }
        return department.get();
    }



    // TODO shuyerga yetd



}
