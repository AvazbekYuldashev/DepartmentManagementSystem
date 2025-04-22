package com.example.department_management_system.service;

import com.example.department_management_system.dto.application.ApplicationDTO;
import com.example.department_management_system.dto.application.ApplicationFilterDTO;
import com.example.department_management_system.entity.ApplicationEntity;
import com.example.department_management_system.enums.AppLangulage;
import com.example.department_management_system.enums.ApplicationStatus;
import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.exp.AppBadRequestExeption;
import com.example.department_management_system.exp.NotFoundExeption;
import com.example.department_management_system.mapper.application.ApplicationMapper;
import com.example.department_management_system.mapper.application.ApplicationMapperDE;
import com.example.department_management_system.repository.application.ApplicationCustomRepository;
import com.example.department_management_system.repository.application.ApplicationRepository;
import com.example.department_management_system.util.CheckAccesss;
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
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private CompletedWorkService completedWorkService;
    @Autowired
    private ApplicationCustomRepository applicationCustomRepository;
    @Autowired
    private CheckAccesss checkAccesss;
    @Autowired
    private ApplicationMapperDE applicationMapper;
    @Autowired
    private ResourceBoundleService boundleService;

    ///  Create application  --> (Yangi ariza yaratish).
    public ApplicationDTO saveApplication(ApplicationDTO applicationDTO) {
        applicationDTO.setCreatedById(SpringSecurityUtil.getCurrentUserId());
        applicationDTO.setCreatedDate(LocalDateTime.now());
        applicationDTO.setUpdatedDate(LocalDateTime.now());
        applicationDTO.setVisible(true);
        applicationDTO.setStatus(ApplicationStatus.SENT);
        ApplicationEntity applicationEntity = applicationMapper.toEntity(applicationDTO);
        return applicationMapper.toDTO(applicationRepository.save(applicationEntity));
    }
    /// Get All --> (Barcha arizalarni olish)
    public List<ApplicationMapper> getAll() {
        checkAccesss.checkAdminAccess();
        return applicationRepository.findAllMapper();
    }
    ///  Get by Id  --> (ID bo‘yicha ApplicationMapperni qaytarish).
    public ApplicationMapper getById(Integer id, AppLangulage lang) {
        return applicationRepository.findByIdMapper(id)
                .orElseThrow(() ->
                        new AppBadRequestExeption(boundleService.getMessage("application.not.found", lang)));
    }
    /// Get By CreatedBy --> (Arizani yuborgan xodim bo‘yicha qidirish).
    public List<ApplicationMapper> findByCreatedBy() {
        return applicationRepository.findByCreatedByMapper(SpringSecurityUtil.getCurrentUserId());
    }
    /// Get By AssingedTo --> (Arizani bajargan xodim bo‘yicha qidirish).
    public List<ApplicationMapper> findByAssignedTo() {
        checkAccesss.checkAdminAccess();
        return applicationRepository.findByAssignedToMapper(SpringSecurityUtil.getCurrentUserId());
    }
    /// Get By Department -->  (Ariza yuborilgan bo‘lim bo‘yicha qidirish).
    public List<ApplicationMapper> findByDepartmentId(Integer id) {
        Integer currentDepartmentId = SpringSecurityUtil.getCurrentDepartmentId();
        if (currentDepartmentId != null && currentDepartmentId.equals(id)) {checkAccesss.checkAdminAccess();}
        return applicationRepository.findByDepartmentMapper(id);
    }
    ///  Get By Status --> (Ariza holati bo‘yicha qidirish).
    public List<ApplicationMapper> findByStatus(ApplicationStatus status){
        checkAccesss.checkAdminAccess();
        checkAccesss.chekStatus(status);
        return applicationRepository.findByStatusMapper(status);
    }
    /// Update Visible --> (Ko‘rinish holatini (visible) yangilash.)
    public Boolean updateVisible(Integer id, Boolean visible, AppLangulage lang) {
        ApplicationEntity entity = getByIdEntity(id, lang); // Bir marta chaqirish
        if (!entity.getCreatedBy().getId().equals(SpringSecurityUtil.getCurrentUserId())) {
            throw new AppBadRequestExeption(boundleService.getMessage("not.permit", lang));
        }
        if (!entity.getStatus().equals(ApplicationStatus.SENT)) {
            throw new AppBadRequestExeption(boundleService.getMessage("not.permit", lang));
        }
        return applicationRepository.updateVisible(id, visible, LocalDateTime.now()) > 0;
    }
    /// Get Paged  --> (Saxifalab korish.)
    public PageImpl<ApplicationMapper> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<ApplicationMapper> pageObj = applicationRepository.findAllPageble(pageable);
        return new PageImpl<>(pageObj.getContent(), pageable, pageObj.getTotalElements());
    }
    /// Get By filter  --> (Filter boyicha malumot olish)
    public PageImpl<ApplicationDTO> filter(ApplicationFilterDTO dto, int page, int size) {
        PageImpl<ApplicationEntity> result = applicationCustomRepository.filter(dto, page, size);
        List<ApplicationDTO> dtoResult = result.getContent()
                .stream()
                .map(applicationMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoResult, PageRequest.of(page, size), result.getTotalElements());
    }
    ///  Get Paged Created By  --> (Yaratgan odamni arizalarini paged korish)
    public PageImpl<ApplicationMapper> getCreatedByPaged(int page, int size){
        Integer id = SpringSecurityUtil.getCurrentUserId();
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ApplicationMapper> pageObj = applicationRepository.findCreatedByPaged(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), id);
        long total = pageObj.getTotalElements();
        return new PageImpl<>(pageObj.getContent(), pageable, total);
    }
    /// Update AssingedTo --> (Bajargan xodimni yangilash).
    public Boolean updateAssignedTo(Integer id, ApplicationDTO dto) {
        if (!SpringSecurityUtil.getCurrentEmployeeRole().equals(EmployeeRole.SUPERADMIN.name())) {
            throw new AppBadRequestExeption("Ruxsat yo'q");
        }
        return applicationRepository.updateAssignedTo(
                id,
                dto.getAssignedToId(),
                ApplicationStatus.APPROVED,
                LocalDateTime.now()
        ) > 0;
    }
    /// Update --> (Holatni yangilash).
    public boolean updateStatus(Integer id, ApplicationDTO dto, AppLangulage lang) {
        ApplicationEntity entity = getByIdEntity(id, lang);
        EmployeeRole role = EmployeeRole.valueOf(SpringSecurityUtil.getCurrentEmployeeRole());

        if (!checkAccesss.hasPermissionToUpdate(role, dto.getStatus())) {
            throw new AppBadRequestExeption(boundleService.getMessage("you.do.not.have.permission.to.change.the.status.to", lang) + dto.getStatus());
        }

        boolean updated = applyStatusUpdate(id, dto.getStatus());

        if (updated && dto.getStatus() == ApplicationStatus.COMPLETED) {
            completedWorkService.saveCompletedWork(entity.getId());
        }
        return updated;
    }
    /// Update status --> (Status-ni yangilash metodini soddalashtirish)
    private boolean applyStatusUpdate(Integer id, ApplicationStatus status) {
        return applicationRepository.updateStatus(id, status, LocalDateTime.now()) > 0;
    }
    /// Get by id ApplicationEntity (Application  ni ID bo‘yicha olish)
    public ApplicationEntity getByIdEntity(Integer id, AppLangulage lang) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption(boundleService.getMessage("application.does.not.exist", lang)));
    }

}