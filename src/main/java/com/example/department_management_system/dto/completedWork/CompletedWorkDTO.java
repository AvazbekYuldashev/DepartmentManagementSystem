package com.example.department_management_system.dto.completedWork;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

///  Bajarilgan ishlar
public class CompletedWorkDTO {
    private Integer id;                     /// ID
    private LocalDateTime createdDate;      /// Yaratilgan sanasi
    private LocalDateTime updatedDate;      /// Yangilangan sanasi
    @NotNull(message = "Application Id required")
    private Integer applicationId;          /// Bajarilgan ishga tegishli ariza ID
    @NotNull(message = "Department Id required")
    private Integer departmentId;           /// Bajarilgan ishga tegishli bo'lim ID
    @NotNull(message = "Employee required")
    private Integer employeeId;             /// Xizmat ko'rsatgan xodim ID
    private Boolean visible;

    public CompletedWorkDTO(){}

    public CompletedWorkDTO(Integer id, LocalDateTime createdDate, LocalDateTime updatedDate, Integer applicationId, Integer departmentId, Integer employeeId, Boolean visible) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.applicationId = applicationId;
        this.departmentId = departmentId;
        this.employeeId = employeeId;
        this.visible = visible;
    }

    /// korinishi



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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