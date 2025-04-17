package com.example.department_management_system.dto.offering;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/// Xizmatlar
public class OfferingDTO {

    private Integer id;                                         /// Id
    private Boolean status;                                     /// Xolati (Ishlayabdi yoki to'xtatilgan)
    @NotBlank(message = "Title required")
    private String title;                                       /// Xizmat nomi
    @NotBlank(message = "Descriotion required")
    private String description;                                 /// Tavsifi
    private LocalDateTime createdDate;                          /// Yaratilgan sanasi
    private LocalDateTime updatedDate;                          /// O'zgartirilgan sanasi
    @NotNull(message = "Department Id required")
    private Integer departmentId;                               /// Bo'lim IDsi
    private List<Integer> applicationIds;                       /// Xizmatga bog'langan arizalar IDlari
    private Boolean visible;

    public OfferingDTO(){}
    public OfferingDTO(Integer id, Boolean status, String title, String description, LocalDateTime createdDate, LocalDateTime updatedDate, Integer departmentId, List<Integer> applicationIds, Boolean visible) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.departmentId = departmentId;
        this.applicationIds = applicationIds;
        this.visible = visible;
    }

    /// korinishi



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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
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
