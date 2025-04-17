package com.example.department_management_system.dto.offering;

import java.time.LocalDate;
import java.util.List;

public class OfferingFilterDTO {
    private Integer id;                                         /// Id
    private Boolean status;                                     /// Xolati (Ishlayabdi yoki to'xtatilgan)
    private String title;                                       /// Xizmat nomi
    private String description;                                 /// Tavsifi
    private LocalDate createdDateFrom;                          /// Yaratilgan sanasi
    private LocalDate createdDateTo;                            /// Yaratilgan sanasi
    private LocalDate updatedDateFrom;                          /// Yangilangan sanasi
    private LocalDate updatedDateTo;                            /// Yangilangan sanasi
    private Integer departmentId;                               /// Bo'lim IDsi
    private List<Integer> applicationIds;                       /// Xizmatga bog'langan arizalar IDlari
    private Boolean visible;

    public OfferingFilterDTO(){}
    public OfferingFilterDTO(Integer id, Boolean status, String title, String description, LocalDate createdDateFrom, LocalDate createdDateTo, LocalDate updatedDateFrom, LocalDate updatedDateTo, Integer departmentId, List<Integer> applicationIds, Boolean visible) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.createdDateFrom = createdDateFrom;
        this.createdDateTo = createdDateTo;
        this.updatedDateFrom = updatedDateFrom;
        this.updatedDateTo = updatedDateTo;
        this.departmentId = departmentId;
        this.applicationIds = applicationIds;
        this.visible = visible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDateFrom() {
        return createdDateFrom;
    }

    public void setCreatedDateFrom(LocalDate createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    public LocalDate getCreatedDateTo() {
        return createdDateTo;
    }

    public void setCreatedDateTo(LocalDate createdDateTo) {
        this.createdDateTo = createdDateTo;
    }

    public LocalDate getUpdatedDateFrom() {
        return updatedDateFrom;
    }

    public void setUpdatedDateFrom(LocalDate updatedDateFrom) {
        this.updatedDateFrom = updatedDateFrom;
    }

    public LocalDate getUpdatedDateTo() {
        return updatedDateTo;
    }

    public void setUpdatedDateTo(LocalDate updatedDateTo) {
        this.updatedDateTo = updatedDateTo;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public List<Integer> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(List<Integer> applicationIds) {
        this.applicationIds = applicationIds;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
