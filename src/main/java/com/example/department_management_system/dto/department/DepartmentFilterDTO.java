package com.example.department_management_system.dto.department;


import com.example.department_management_system.dto.employee.EmployeeDTO;
import com.example.department_management_system.dto.offering.OfferingDTO;
import com.example.department_management_system.dto.application.ApplicationDTO;
import com.example.department_management_system.dto.completedWork.CompletedWorkDTO;
import com.example.department_management_system.enums.DepartmentStatus;
import com.example.department_management_system.enums.DepartmentType;

import java.time.LocalDate;
import java.util.List;

///  Bolim
public class DepartmentFilterDTO {
    private Integer id;                                     /// Id
    private DepartmentStatus status;                        /// Xozirgi xolati
    private String title;                                   /// Bo'lim nomi
    private String description;                             /// Bo'lim xaqida qisqacha
    private LocalDate createdDateFrom;                      /// Yaratilgan sanasi
    private LocalDate createdDateTo;
    private LocalDate updatedDateFrom;                      /// Yangilangan sanasi
    private LocalDate updatedDateTo;
    private String address;                                 /// Bo'lim manzili
    private String phoneNumber;                             /// Bo'lim telefon raqami
    private String headOfDepartment;                        /// Bo'lim boshlig'ining ismi yoki ID-si
    private DepartmentType type;                            /// Bo'lim turi (masalan, "Akademik", "Texnik")
    private List<EmployeeDTO> employeeIds;                  /// Bo'lim tarkibidagi xodimlar IDlari
    private List<OfferingDTO> offeringIds;                  /// Bo'lim tarkibidagi xizmatlar IDlari
    private List<ApplicationDTO> applicationIds;            /// Bo'limga kelgan arizalar IDlari
    private List<CompletedWorkDTO> completedWorkIds;        /// Bo'limda bajarilgan ishlar IDlari
    private Boolean visible;

    public DepartmentFilterDTO(){}

    public DepartmentFilterDTO(Integer id, DepartmentStatus status, String title, String description, LocalDate createdDateFrom, LocalDate createdDateTo, LocalDate updatedDateFrom, LocalDate updatedDateTo, String address, String phoneNumber, String headOfDepartment, DepartmentType type, List<EmployeeDTO> employeeIds, List<OfferingDTO> offeringIds, List<ApplicationDTO> applicationIds,  List<CompletedWorkDTO> completedWorkIds, Boolean visible) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.createdDateFrom = createdDateFrom;
        this.createdDateTo = createdDateTo;
        this.updatedDateFrom = updatedDateFrom;
        this.updatedDateTo = updatedDateTo;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.headOfDepartment = headOfDepartment;
        this.type = type;
        this.employeeIds = employeeIds;
        this.offeringIds = offeringIds;
        this.applicationIds = applicationIds;
        this.completedWorkIds = completedWorkIds;
        this.visible = visible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartmentStatus getStatus() {
        return status;
    }

    public void setStatus(DepartmentStatus status) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public DepartmentType getType() {
        return type;
    }

    public void setType(DepartmentType type) {
        this.type = type;
    }

    public List<EmployeeDTO> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<EmployeeDTO> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<OfferingDTO> getOfferingIds() {
        return offeringIds;
    }

    public void setOfferingIds(List<OfferingDTO> offeringIds) {
        this.offeringIds = offeringIds;
    }

    public List<ApplicationDTO> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(List<ApplicationDTO> applicationIds) {
        this.applicationIds = applicationIds;
    }

    public List<CompletedWorkDTO> getCompletedWorkIds() {
        return completedWorkIds;
    }

    public void setCompletedWorkIds(List<CompletedWorkDTO> completedWorkIds) {
        this.completedWorkIds = completedWorkIds;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
