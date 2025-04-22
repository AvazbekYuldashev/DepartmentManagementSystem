package com.example.department_management_system.service;


import com.example.department_management_system.dto.offering.OfferingDTO;
import com.example.department_management_system.dto.offering.OfferingFilterDTO;
import com.example.department_management_system.entity.DepartmentEntity;
import com.example.department_management_system.entity.OfferingEntity;
import com.example.department_management_system.enums.AppLangulage;
import com.example.department_management_system.exp.AppBadRequestExeption;
import com.example.department_management_system.mapper.department.DepartmentMapper;
import com.example.department_management_system.mapper.offering.OfferingMapper;
import com.example.department_management_system.mapper.offering.OfferingMapperDE;
import com.example.department_management_system.repository.department.DepartmentRepository;
import com.example.department_management_system.repository.offering.OfferingCustomRepository;
import com.example.department_management_system.repository.offering.OfferingRepository;
import com.example.department_management_system.util.CheckAccesss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferingService {
    @Autowired
    @Lazy
    private OfferingRepository offeringRepository;
    @Autowired
    private OfferingCustomRepository offeringCustomRepository;
    @Autowired
    private CheckAccesss checkAccesss;
    @Autowired
    private OfferingMapperDE offeringMapper;
    @Autowired
    private DepartmentService departmentService;

    /// Create
    public OfferingDTO create(OfferingDTO offeringDTO) {
        checkAccesss.checkAdminAccess();
        offeringDTO.setCreatedDate(LocalDateTime.now());
        offeringDTO.setUpdatedDate(LocalDateTime.now());
        offeringDTO.setVisible(true);
        offeringDTO.setStatus(true);
        return offeringMapper.toDTO(offeringRepository.save(offeringMapper.toEntity(offeringDTO)));
    }

    /// Get All
    public List<OfferingMapper> getAll() {
        return offeringRepository.findAllMapper();
    }

    ///  Get By Id
    public OfferingMapper getById(Integer id) {
        return offeringRepository.findByIdMapper(id).get();
    }

    ///  Get By Department
    public List<OfferingMapper> getByDepartmentId(Integer departmentId) {
        return offeringRepository.findByDepartmentMapper(departmentId);
    }

    public Boolean updateStatus(Integer id, OfferingDTO offeringDTO) {
        checkAccesss.checkAdminAccess();
        int effectedRow = offeringRepository.updateStatus(id, offeringDTO.getStatus(), LocalDateTime.now());
        return effectedRow > 0;
    }

    public Boolean updateDepartmentIdP(Integer id, OfferingDTO offeringDTO, AppLangulage lang) {
        checkAccesss.checkAdminAccess();
        departmentService.getById(offeringDTO.getDepartmentId(), lang);
        int effectedRow = offeringRepository.updateDepartmentIdP(id, offeringDTO.getDepartmentId(), LocalDateTime.now());
        return effectedRow > 0;
    }

    public Boolean updateOffering(Integer id, OfferingDTO offeringDTO) {
        checkAccesss.checkAdminAccess();
        int effectedRow = offeringRepository.updateOffering(id, offeringDTO.getStatus(),
                offeringDTO.getTitle(), offeringDTO.getDescription(),
                offeringDTO.getDepartmentId(), LocalDateTime.now());
        return effectedRow > 0;
    }

    public Boolean deleteWipe(Integer id, Boolean visible) {
        checkAccesss.checkAdminAccess();
        int effectedRow = offeringRepository.updateVisible(id, visible, LocalDateTime.now());
        return effectedRow > 0;
    }

    public Boolean deleteById(Integer id) {
        checkAccesss.checkAdminAccess();
        offeringRepository.deleteById(id);
        return true;
    }

    public PageImpl<OfferingMapper> pagination(int page, int size){
        checkAccesss.checkAdminAccess();
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<OfferingMapper> pageObj = offeringRepository.findAllPageble(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        long total = pageObj.getTotalElements();
        return new PageImpl<>(pageObj.getContent(), pageable, total);
    }

    public PageImpl<OfferingMapper> paginationByDepartmentId(int page, int size, Integer departmentId) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<OfferingMapper> pageObj = offeringRepository.findAllByDepartmentIdPageble(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), departmentId);
        long total = pageObj.getTotalElements();
        return new PageImpl<>(pageObj.getContent(), pageable, total);
    }

    public PageImpl<OfferingDTO> filter(OfferingFilterDTO dto, int page, int size) {
        PageImpl<OfferingEntity> result = offeringCustomRepository.filter(dto, page, size);
        List<OfferingDTO> dtoResult = new LinkedList<>();
        for (OfferingEntity entity : result){dtoResult.add(offeringMapper.toDTO(entity));}
        return new PageImpl<>(dtoResult, PageRequest.of(page, size), result.getTotalElements());
    }



}
