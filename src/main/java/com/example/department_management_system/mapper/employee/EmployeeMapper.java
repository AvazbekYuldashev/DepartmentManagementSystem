package com.example.department_management_system.mapper.employee;



import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.enums.EmployeeStatus;

import java.time.LocalDateTime;

public interface EmployeeMapper {

    Integer getId();                                                    /// Id si
    String getName();                                                   /// Ismi
    String getSurname();                                                /// Familyasi
    EmployeeRole getRole();                                             /// Roli
    String getPosition();                                               /// Lavozimi
    EmployeeStatus getStatus();                                         /// Statusi (Blok yoki aktiv)
    LocalDateTime getCreatedDate();                                     /// Yaratilgan sanasi
    LocalDateTime getUpdatedDate();                                     /// Yangilangan sanasi
    Integer getDepartment();                                            /// Bo'lim IDsi
    String getDepartmentTitle();                                        /// Bo'lim nomi
    Boolean getVisible();                                               /// korinishi
    String getEmail();
    String getPassword();
}
