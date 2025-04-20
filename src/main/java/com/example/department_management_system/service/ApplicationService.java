package com.example.department_management_system.service;

import com.example.department_management_system.dto.application.ApplicationDTO;
import com.example.department_management_system.dto.application.ApplicationFilterDTO;
import com.example.department_management_system.entity.ApplicationEntity;
import com.example.department_management_system.enums.ApplicationStatus;
import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.exp.AppBadRequestExeption;
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

@Service
public class ApplicationService {
    @Autowired
    @Lazy
    private ApplicationRepository applicationRepository;
    @Autowired
    @Lazy
    private CompletedWorkService completedWorkService;
    @Autowired
    private ApplicationCustomRepository applicationCustomRepository;
    @Autowired
    private CheckAccesss checkAccesss;
    @Autowired
    private ApplicationMapperDE applicationMapper;

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
    public ApplicationMapper getById(Integer id) {
        checkAccesss.checkAdminAccess();
        Optional<ApplicationMapper> applicationMapper = applicationRepository.findByIdMapper(id);
        if (applicationMapper.isEmpty()) {return null;}
        return applicationMapper.get();
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
    public Boolean updateVisible(Integer id, Boolean visible) {
        ApplicationEntity application = getByIdEntity(id);
        if (application.getCreatedBy().getId().equals(SpringSecurityUtil.getCurrentUserId())){
            ApplicationEntity entity = getByIdEntity(id);
            if (!entity.getStatus().equals(ApplicationStatus.SENT)){
                throw new AppBadRequestExeption("This user cannot this application delete");
            }
            int effectedRow = applicationRepository.updateVisible(id, visible, LocalDateTime.now());
            return effectedRow > 0;
        }
        throw new AppBadRequestExeption("It does not belong to the current profile.");
    }
    /// Get Paged  --> (Saxifalab korish.)
    public PageImpl<ApplicationMapper> pagination(int page, int size){
        checkAccesss.checkAdminAccess();
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ApplicationMapper> pageObj = applicationRepository.findAllPageble(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        long total = pageObj.getTotalElements();
        return new PageImpl<ApplicationMapper>(pageObj.getContent(), pageable, total);
    }
    /// Get By filter  --> (Filter boyicha malumot olish)
    public PageImpl<ApplicationDTO> filter(ApplicationFilterDTO dto, int page, int size) {
        checkAccesss.chekStatus(dto.getStatus());
        PageImpl<ApplicationEntity> result = applicationCustomRepository.filter(dto, page, size);
        List<ApplicationDTO> dtoResult = new LinkedList<>();
        for (ApplicationEntity entity : result){
            System.out.println(entity.toString());
            dtoResult.add(applicationMapper.toDTO(entity));}
        return new PageImpl<>(dtoResult, PageRequest.of(page, size), result.getTotalElements());
    }
    ///  Get Paged Created By  --> (Yaratgan odamni arizalarini paged korish)
    public PageImpl<ApplicationMapper> getCreatedByPaged(int page, int size){
        Integer id = SpringSecurityUtil.getCurrentUserId();
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ApplicationMapper> pageObj = applicationRepository.findCreatedByPaged(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), id);
        long total = pageObj.getTotalElements();
        return new PageImpl<ApplicationMapper>(pageObj.getContent(), pageable, total);
    }
    /// Update AssingedTo --> (Bajargan xodimni yangilash).
    public Boolean  updateAssignedTo(Integer id, ApplicationDTO applicationDTO) {
        checkAccesss.checkAdminAccess();
        if (SpringSecurityUtil.getCurrentEmployeeRole().equals(EmployeeRole.SUPERADMIN.toString())){
            int effectedRow = applicationRepository.updateAssignedTo(id, applicationDTO.getAssignedToId(), ApplicationStatus.APPROVED, LocalDateTime.now());
            return effectedRow > 0;
        }else {throw new AppBadRequestExeption("This User this application cannot be modified.");}
    }

    /// Update --> (Holatni yangilash).
    public boolean updateStatus(Integer id, ApplicationDTO applicationDTO) {
        ApplicationEntity application = getByIdEntity(id);
        EmployeeRole userRole = EmployeeRole.valueOf(SpringSecurityUtil.getCurrentEmployeeRole());

        if (applicationDTO.getStatus() == null) {
            throw new AppBadRequestExeption("Please indicate the status of the application.");
        }

        // Statusni ENUM formatiga o'tkazish
        ApplicationStatus status = applicationDTO.getStatus();
        checkAccesss.chekStatus(status); // Statusni tekshirish

        // Ruxsatni tekshirish
        if (!checkAccesss.hasPermissionToUpdate(userRole, status)) {
            throw new AppBadRequestExeption("You do not have permission to change the status to " + status);
        }

        // Statusni yangilash
        boolean isUpdated = applyStatusUpdate(id, status);

        // Agar status COMPLETED bo‘lsa, qo‘shimcha saqlash
        if (isUpdated && status == ApplicationStatus.COMPLETED) {
            completedWorkService.saveCompletedWork(application.getId());
        }
        
        return isUpdated;
    }

    /// Update status --> (Status-ni yangilash metodini soddalashtirish)
    private boolean applyStatusUpdate(Integer id, ApplicationStatus status) {
        return applicationRepository.updateStatus(id, status, LocalDateTime.now()) > 0;
    }

    /// Get by id ApplicationEntity (Application  ni ID bo‘yicha olish)
    public ApplicationEntity getByIdEntity(Integer id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new AppBadRequestExeption("Application does not exist."));
    }

}