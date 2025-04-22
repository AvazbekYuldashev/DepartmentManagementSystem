package com.example.department_management_system.dto.employee;

import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.enums.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeFilterDTO {
    private Integer id;                                                   /// Id si
    private String name;                                                  /// Ismi
    private String surname;                                               /// Familyasi
    private EmployeeRole role;                                            /// Roli
    private String position;                                              /// Lavozimi
    private EmployeeStatus status;                                        /// Statusi (Blok yoki aktiv)
    private LocalDate createdDateFrom;                                    /// Yaratilgan sanasi
    private LocalDate createdDateTo;                                      /// Yaratilgan sanasi
    private LocalDate updatedDateFrom;                                    /// Yangilangan sanasi
    private LocalDate updatedDateTo;                                      /// Yangilangan sanasi
    private Integer departmentId;                                         /// Bo'lim IDsi
    private Boolean visible;                                              /// korinishi
    private String email;
    private String password;
}
