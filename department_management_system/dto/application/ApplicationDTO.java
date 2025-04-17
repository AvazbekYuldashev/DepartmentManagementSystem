package com.example.department_management_system.dto.application;


import com.example.department_management_system.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/// Arizalar
public class ApplicationDTO {

    private Integer id;                     /// ID
    @NotBlank(message = "Title required")
    private String title;                   /// Ariza turi
    @NotBlank(message = "Description required")
    private String description;             /// Ariza matni
    private LocalDateTime createdDate;      /// Ariza yaratilgan sanasi
    private LocalDateTime updatedDate;      /// Ariza o'zgartirilgan sanasi
    @NotNull(message = "Offering Id required")
    private Integer offeringId;             /// Xizmat ID
    private Integer createdById;            /// Arizani yuborgan xodim ID
    private Integer assignedToId;           /// Arizani ko'rib chiqadigan xodim ID
    private Integer departmentId;           /// Ariza yuborilayotgan bo'lim ID
    private ApplicationStatus status;       /// Ariza xolati
    private Integer completedWorkId;        /// Ariza bajarilganligi xaqida ma'lumot
    private Boolean visible;

    public ApplicationDTO(){}
    public ApplicationDTO(Integer id, String title, String description, LocalDateTime createdDate, LocalDateTime updatedDate, Integer offeringId, Integer createdById, Integer assignedToId, Integer departmentId, ApplicationStatus status, Integer completedWorkId, Boolean visible) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.offeringId = offeringId;
        this.createdById = createdById;
        this.assignedToId = assignedToId;
        this.departmentId = departmentId;
        this.status = status;
        this.completedWorkId = completedWorkId;
        this.visible = visible;
    }

    /// korinishi


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(Integer offeringId) {
        this.offeringId = offeringId;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public Integer getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Integer assignedToId) {
        this.assignedToId = assignedToId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Integer getCompletedWorkId() {
        return completedWorkId;
    }

    public void setCompletedWorkId(Integer completedWorkId) {
        this.completedWorkId = completedWorkId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
