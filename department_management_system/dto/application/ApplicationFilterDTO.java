package com.example.department_management_system.dto.application;


import com.example.department_management_system.enums.ApplicationStatus;

import java.time.LocalDate;

public class ApplicationFilterDTO {
    private Integer id;                     /// ID
    private String title;                   /// Ariza turi
    private String description;             /// Ariza matni
    private LocalDate createdDateFrom;      /// Ariza yaratilgan sanasi
    private LocalDate createdDateTo;        /// Ariza yaratilgan sanasi
    private LocalDate updatedDateFrom;      /// Ariza o'zgartirilgan sanasi
    private LocalDate updatedDateTo;        /// Ariza o'zgartirilgan sanasi
    private Integer offeringId;             /// Xizmat ID
    private Integer createdById;            /// Arizani yuborgan xodim ID
    private Integer assignedToId;           /// Arizani ko'rib chiqadigan xodim ID
    private Integer departmentId;           /// Ariza yuborilayotgan bo'lim ID
    private ApplicationStatus status;       /// Ariza xolati
    private Integer completedWorkId;        /// Ariza bajarilganligi xaqida ma'lumot
    private Boolean visible;

    public ApplicationFilterDTO(){}
    public ApplicationFilterDTO(Integer id, String title, String description, LocalDate createdDateFrom, LocalDate createdDateTo, LocalDate updatedDateFrom, LocalDate updatedDateTo, Integer offeringId, Integer createdById, Integer assignedToId, Integer departmentId, ApplicationStatus status, Integer completedWorkId, Boolean visible) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDateFrom = createdDateFrom;
        this.createdDateTo = createdDateTo;
        this.updatedDateFrom = updatedDateFrom;
        this.updatedDateTo = updatedDateTo;
        this.offeringId = offeringId;
        this.createdById = createdById;
        this.assignedToId = assignedToId;
        this.departmentId = departmentId;
        this.status = status;
        this.completedWorkId = completedWorkId;
        this.visible = visible;
    }

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
