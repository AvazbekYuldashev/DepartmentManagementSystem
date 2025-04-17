package com.example.department_management_system.dto.employee;

import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.enums.EmployeeStatus;

import java.time.LocalDateTime;

public class EmployeeUpdateDTO {
    private Integer id;                                                   /// Id si
    private String name;                                                  /// Ismi
    private String surname;                                               /// Familyasi
    private EmployeeRole role;                                            /// Roli
    private String position;                                              /// Lavozimi
    private EmployeeStatus status;                                        /// Statusi (Blok yoki aktiv)
    private LocalDateTime createdDate;                                    /// Yaratilgan sanasi
    private LocalDateTime updatedDate;                                    /// Yangilangan sanasi
    private Integer departmentId;                                         /// Bo'lim IDsi
    private Boolean visible;                                              /// korinishi
    private String email;
    private String password;

    public EmployeeUpdateDTO(){}
    public EmployeeUpdateDTO(Integer id, String name, String surname, EmployeeRole role, String position, EmployeeStatus status, LocalDateTime createdDate, LocalDateTime updatedDate, Integer departmentId, Boolean visible, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.position = position;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.departmentId = departmentId;
        this.visible = visible;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
