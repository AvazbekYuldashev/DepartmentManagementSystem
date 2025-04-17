package com.example.department_management_system.service;

import com.example.department_management_system.dto.application.ApplicationDTO;
import com.example.department_management_system.dto.application.ApplicationFilterDTO;
import com.example.department_management_system.entity.ApplicationEntity;
import com.example.department_management_system.enums.ApplicationStatus;
import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.exp.AppBadRequestExeption;
import com.example.department_management_system.mapper.ApplicationMapper;
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
    private OfferingService offeringService;
    @Autowired
    @Lazy
    private EmployeeService employeeService;
    @Autowired
    @Lazy
    private DepartmentService departmentService;
    @Autowired
    @Lazy
    private CompletedWorkService completedWorkService;
    @Autowired
    private ApplicationCustomRepository applicationCustomRepository;
    @Autowired
    private CheckAccesss checkAccesss;

    ///  Create application  --> (Yangi ariza yaratish).
    public ApplicationDTO saveApplication(ApplicationDTO applicationDTO) {
        departmentService.getByIdEntity(1);
        applicationDTO.setCreatedById(SpringSecurityUtil.getCurrentUserId());
        applicationDTO.setCreatedDate(LocalDateTime.now());
        applicationDTO.setUpdatedDate(LocalDateTime.now());
        applicationDTO.setVisible(true);
        applicationDTO.setStatus(ApplicationStatus.SENT);
        ApplicationEntity applicationEntity = toEntity(applicationDTO);
        return toDTO(applicationRepository.save(applicationEntity));
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
        chekStatus(status);
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
        chekStatus(dto.getStatus());
        PageImpl<ApplicationEntity> result = applicationCustomRepository.filter(dto, page, size);
        List<ApplicationDTO> dtoResult = new LinkedList<>();
        for (ApplicationEntity entity : result){
            System.out.println(entity.toString());
            dtoResult.add(toDTO(entity));}
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
        chekStatus(status); // Statusni tekshirish

        // Ruxsatni tekshirish
        if (!hasPermissionToUpdate(userRole, status)) {
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

    // **Foydalanuvchi rolini tekshirish**
    private boolean hasPermissionToUpdate(EmployeeRole userRole, ApplicationStatus status) {
        return (userRole == EmployeeRole.SUPERADMIN && (status == ApplicationStatus.APPROVED || status == ApplicationStatus.REJECTED))
                || (userRole == EmployeeRole.ADMIN && (status == ApplicationStatus.IN_PROGRESS || status == ApplicationStatus.COMPLETED));
    }

    // **Status yangilash metodini soddalashtirish**
    private boolean applyStatusUpdate(Integer id, ApplicationStatus status) {
        return applicationRepository.updateStatus(id, status, LocalDateTime.now()) > 0;
    }

    // **ApplicationEntity ni ID bo‘yicha olish**
    public ApplicationEntity getByIdEntity(Integer id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new AppBadRequestExeption("Application does not exist."));
    }
    ///  ToDTO --> (Entity'ni DTO'ga o‘tkazish).
    public ApplicationDTO toDTO(ApplicationEntity entity) {
        if (entity == null) {return null;}
        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());
        dto.setStatus(entity.getStatus());
        if (entity.getOffering() != null){dto.setOfferingId(entity.getOffering().getId());}
        if (entity.getCreatedBy() != null){dto.setCreatedById(entity.getCreatedBy().getId());}
        if (entity.getAssignedTo() != null){dto.setAssignedToId(entity.getAssignedTo().getId());}
        if (entity.getDepartment() != null){dto.setDepartmentId(entity.getDepartment().getId());}
        if (entity.getCompletedWork() != null) {dto.setCompletedWorkId(entity.getCompletedWork().getId());}
        return dto;
    }
    ///  ToEntity --> (DTO'ni Entity'ga o‘tkazish).
    public ApplicationEntity toEntity(ApplicationDTO dto) {
        if (dto == null) {return null;}
        ApplicationEntity entity = new ApplicationEntity();
        entity.setVisible(dto.getVisible());
        if (dto.getId() != null) {entity.setId(dto.getId());}
        if (dto.getTitle() != null) {entity.setTitle(dto.getTitle());}
        if (dto.getDescription() != null) {entity.setDescription(dto.getDescription());}
        if (dto.getCreatedDate() != null) {entity.setCreatedDate(dto.getCreatedDate());}
        if (dto.getUpdatedDate() != null) {entity.setUpdatedDate(dto.getUpdatedDate());}
        if (dto.getStatus() != null) {entity.setStatus(dto.getStatus());}
        if (dto.getOfferingId() != null) {entity.setOffering(offeringService.getByIdEntity(dto.getOfferingId()));}
        if (dto.getCreatedById() != null) {entity.setCreatedBy(employeeService.getByIdEntity(dto.getCreatedById()));}
        if (dto.getAssignedToId() != null) {entity.setAssignedTo(employeeService.getByIdEntity(dto.getAssignedToId()));}
        if (dto.getDepartmentId() != null) {entity.setDepartment(departmentService.getByIdEntity(dto.getDepartmentId()));}
        if (dto.getCompletedWorkId() != null) {entity.setCompletedWork(completedWorkService.getByIdEntity(dto.getCompletedWorkId()));}
        return entity;
    }

    private void chekStatus(ApplicationStatus status){
        if (!(status.equals(ApplicationStatus.SENT) ||
                status.equals(ApplicationStatus.APPROVED) ||
                status.equals(ApplicationStatus.IN_PROGRESS) ||
                status.equals(ApplicationStatus.COMPLETED) ||
                status.equals(ApplicationStatus.REJECTED))) {
            throw new IllegalArgumentException("Invalid application status: " + status);}
    }
    private Boolean adminAccess() {
        if (SpringSecurityUtil.getCurrentEmployeeRole().equals(EmployeeRole.SUPERADMIN.toString())) {return true;}
        if (SpringSecurityUtil.getCurrentEmployeeRole().equals(EmployeeRole.ADMIN.toString())) {return false;}
        throw new AppBadRequestExeption("It does not belong to the current profile.");
    }
}