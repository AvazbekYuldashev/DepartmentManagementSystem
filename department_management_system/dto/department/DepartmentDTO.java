package com.example.department_management_system.dto.department;


import com.example.department_management_system.dto.employee.EmployeeDTO;
import com.example.department_management_system.dto.offering.OfferingDTO;
import com.example.department_management_system.dto.application.ApplicationDTO;
import com.example.department_management_system.dto.completedWork.CompletedWorkDTO;
import com.example.department_management_system.enums.DepartmentStatus;
import com.example.department_management_system.enums.DepartmentType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

///  Bolim
@Getter
@Setter
public class DepartmentDTO {
    private Integer id;                                          /// Id
    private DepartmentStatus status;                             /// Xozirgi xolati
    @NotBlank(message = "Title required")
    private String title;                                        /// Bo'lim nomi
    @NotBlank(message = "Description required")
    private String description;                                  /// Bo'lim xaqida qisqacha
    private LocalDateTime createdDate;                           /// Yaratilgan sanasi
    private LocalDateTime updatedDate;                           /// Yangilangan sanasi
    @NotBlank(message = "Address required")
    private String address;                                      /// Bo'lim manzili
    @NotBlank(message = "Phone Number required")
    private String phoneNumber;                                  /// Bo'lim telefon raqami
    private String headOfDepartment;                             /// Bo'lim boshlig'ining ismi yoki ID-si
    private DepartmentType type;                                 /// Bo'lim turi (masalan, "Akademik", "Texnik")
    private List<EmployeeDTO> employeeIds;                       /// Bo'lim tarkibidagi xodimlar IDlari
    private List<OfferingDTO> offeringIds;                       /// Bo'lim tarkibidagi xizmatlar IDlari
    private List<ApplicationDTO> applicationIds;                 /// Bo'limga kelgan arizalar IDlari
    private List<CompletedWorkDTO> completedWorkIds;             /// Bo'limda bajarilgan ishlar IDlari
    private Boolean visible;

}
