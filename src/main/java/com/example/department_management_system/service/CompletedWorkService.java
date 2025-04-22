package com.example.department_management_system.service;

import com.example.department_management_system.dto.completedWork.CompletedWorkDTO;
import com.example.department_management_system.dto.completedWork.CompletedWorkFilterDTO;
import com.example.department_management_system.entity.CompletedWorkEntity;
import com.example.department_management_system.enums.AppLangulage;
import com.example.department_management_system.exp.AppBadRequestExeption;
import com.example.department_management_system.mapper.completedWork.CompletedWorkMapper;
import com.example.department_management_system.mapper.completedWork.CompletedWorkMapperDE;
import com.example.department_management_system.repository.compledetWork.CompletedWorkCustomRepository;
import com.example.department_management_system.repository.compledetWork.CompletedWorkRepository;
import com.example.department_management_system.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  CompletedWorkService {
    private final CompletedWorkRepository completedWorkRepository;
    private final CompletedWorkCustomRepository completedWorkCustomRepository;
    private final CompletedWorkMapperDE completedWorkMapperDE;
    private final ResourceBoundleService boundleService;

    @Autowired
    public CompletedWorkService(
            CompletedWorkRepository completedWorkRepository,
            CompletedWorkCustomRepository completedWorkCustomRepository,
            CompletedWorkMapperDE completedWorkMapperDE,
            ResourceBoundleService boundleService
    ) {
        this.completedWorkRepository = completedWorkRepository;
        this.completedWorkCustomRepository = completedWorkCustomRepository;
        this.completedWorkMapperDE = completedWorkMapperDE;
        this.boundleService = boundleService;
    }

    ///  Create CompletedWork  --> (Yangi bajarilgan ish yaratish).
    public void saveCompletedWork(Integer id) {
        CompletedWorkEntity entity = new CompletedWorkEntity();
        entity.setApplicationId(id);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setEmployeeId(SpringSecurityUtil.getCurrentUserId());
        entity.setDepartmentId(SpringSecurityUtil.getCurrentDepartmentId());
        completedWorkRepository.save(entity);
    }
    /// Get All --> (Barcha bajarilgan ishlarni olish).
    public List<CompletedWorkMapper> getAll(){
        return completedWorkRepository.findAllMapper();
    }

    ///  Get by Id  --> (ID bo‘yicha bajarilgan ishni qaytarish).
    public CompletedWorkMapper getById(Integer id, AppLangulage lang) {
        return completedWorkRepository.findByIdMapper(id)
                .orElseThrow(() -> new AppBadRequestExeption(boundleService.getMessage("completed.work.found", lang)));
    }
    /// Get by Application --> (Ariza Id boyicha Barcha bajarilgan ishlarni olish)
    public List<CompletedWorkMapper> findByApplication(Integer applicationId) {
        return completedWorkRepository.findByApplicationMapper(applicationId);
    }
    /// Get by Department --> (Bo'lim Id boyicha Barcha bajarilgan ishlarni olish)
    public List<CompletedWorkMapper> findByDepartmentId(Integer departmentId) {
        return completedWorkRepository.findByDepartmentIdMapper(departmentId);
    }
    /// Get by Employee --> (Xodim Id boyicha Barcha bajarilgan ishlarni olish)
    public List<CompletedWorkMapper> findByEmployeeId(Integer employeeId) {
        return completedWorkRepository.findByEmployeeIdMapper(employeeId);
    }

    /// Update Visible --> (Ko‘rinish holatini (visible) yangilash.)
    public Boolean updateVisibility(Integer id, Boolean visible) {
        int effectedRow = completedWorkRepository.updateVisible(id, visible, LocalDateTime.now());
        return effectedRow > 0;
    }

    public PageImpl<CompletedWorkMapper> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CompletedWorkMapper> pageObj = completedWorkRepository.findAllPageble(pageable);
        return new PageImpl<>(pageObj.getContent(), pageable, pageObj.getTotalElements());
    }
    public PageImpl<CompletedWorkDTO> filter(CompletedWorkFilterDTO dto, int page, int size) {
        PageImpl<CompletedWorkEntity> result = completedWorkCustomRepository.filter(dto, page, size);
        List<CompletedWorkDTO> dtoResult = result.getContent()
                .stream()
                .map(completedWorkMapperDE::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoResult, PageRequest.of(page, size), result.getTotalElements());
    }

}
