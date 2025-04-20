package com.example.department_management_system.dto.employee;


import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.enums.EmployeeStatus;
import com.example.department_management_system.enums.GeneralStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

///  Xodimlar
@Getter
@Setter
public class EmployeeDTO {
    private Integer id;                                                   /// Id si
    @NotBlank(message = "Name required")
    private String name;                                                  /// Ismi
    @NotBlank(message = "Surname required")
    private String surname;                                               /// Familyasi
    private EmployeeRole role;                                            /// Roli
    private String position;                                              /// Lavozimi
    private GeneralStatus status;                                        /// Statusi (Blok yoki aktiv)
    private LocalDateTime createdDate;                                    /// Yaratilgan sanasi
    private LocalDateTime updatedDate;                                    /// Yangilangan sanasi
    private Integer departmentId;                                         /// Bo'lim IDsi
    private Boolean visible;                                              /// korinishi
    @NotBlank(message = "Email required")
    private String email;
    @NotBlank(message = "Password required")
    private String password;
}
