package com.example.department_management_system.dto.completedWork;

import java.time.LocalDate;

///  Bajarilgan ishlar
public class CompletedWorkFilterDTO {
    private Integer id;                         /// ID
    private LocalDate createdDateFrom;          /// Yaratilgan sanasi boshi
    private LocalDate createdDateTo;            /// Yaratilgan sanasi oxiri
    private LocalDate updatedDateFrom;          /// Yangilangan sanasi boshi
    private LocalDate updatedDateTo;            /// Yangilangan sanasi oxiri
    private Integer applicationId;              /// Bajarilgan ishga tegishli ariza ID
    private Integer departmentId;               /// Bajarilgan ishga tegishli bo'lim ID
    private Integer employeeId;                 /// Xizmat ko'rsatgan xodim ID
    private Boolean visible;

    public CompletedWorkFilterDTO(){}

    public CompletedWorkFilterDTO(Integer id, LocalDate createdDateFrom, LocalDate createdDateTo, LocalDate updatedDateFrom, LocalDate updatedDateTo, Integer applicationId, Integer departmentId, Integer employeeId, Boolean visible) {
        this.id = id;
        this.createdDateFrom = createdDateFrom;
        this.createdDateTo = createdDateTo;
        this.updatedDateFrom = updatedDateFrom;
        this.updatedDateTo = updatedDateTo;
        this.applicationId = applicationId;
        this.departmentId = departmentId;
        this.employeeId = employeeId;
        this.visible = visible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}