package com.example.department_management_system.dto.department;

import com.example.department_management_system.enums.DepartmentStatus;
import com.example.department_management_system.enums.DepartmentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentUpdateDTO {
    private String title;               /// Bo'lim nomi
    private String description;         /// Bo'lim xaqida qisqacha
    private String address;             /// Bo'lim manzili
    private String phoneNumber;         /// Bo'lim telefon raqami
    private String headOfDepartment;    /// Bo'lim boshlig'ining ismi yoki ID-si
    private DepartmentType type;        /// Bo'lim turi (masalan, "Akademik", "Texnik")
    private DepartmentStatus status;    /// Bo'lim xolati
}
