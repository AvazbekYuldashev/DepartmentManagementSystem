package com.example.department_management_system.dto.department;


import com.example.department_management_system.dto.employee.EmployeeDTO;
import com.example.department_management_system.dto.offering.OfferingDTO;
import com.example.department_management_system.dto.application.ApplicationDTO;
import com.example.department_management_system.dto.completedWork.CompletedWorkDTO;
import com.example.department_management_system.enums.DepartmentStatus;
import com.example.department_management_system.enums.DepartmentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

///  Bolim
@Getter
@Setter
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
}
