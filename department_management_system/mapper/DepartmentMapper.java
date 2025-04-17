package com.example.department_management_system.mapper;



import com.example.department_management_system.enums.DepartmentStatus;
import com.example.department_management_system.enums.DepartmentType;

import java.time.LocalDateTime;

public interface DepartmentMapper {
    Integer getId();                /// Id
    DepartmentStatus getStatus();   /// Xozirgi xolati
    String getTitle();              /// Bo'lim nomi
    String getDescription();        /// Bo'lim xaqida qisqacha
    LocalDateTime getCreatedDate(); /// Yaratilgan sanasi
    LocalDateTime getUpdatedDate(); /// Yangilangan sanasi
    String getAddress();            /// Bo'lim manzili
    String getPhoneNumber();        /// Bo'lim telefon raqami
    String getHeadOfDepartment();   /// Bo'lim boshlig'ining ismi yoki ID-si
    DepartmentType getType();       /// Bo'lim turi (masalan, "Akademik", "Texnik")
}
