package com.example.department_management_system.dto.department;

import com.example.department_management_system.enums.DepartmentType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentCreateDTO {
    @NotBlank(message = "Title required")
    private String title;                                        /// Bo'lim nomi
    @NotBlank(message = "Description required")
    private String description;                                  /// Bo'lim xaqida qisqacha
    @NotBlank(message = "Address required")
    private String address;                                      /// Bo'lim manzili
    @NotBlank(message = "Phone Number required")
    private String phoneNumber;                                  /// Bo'lim telefon raqami
    @NotBlank(message = "Head of Department required")
    private String headOfDepartment;                             /// Bo'lim boshlig'ining ismi yoki ID-si
    @NotBlank(message = "Type required")
    private DepartmentType type;                                 /// Bo'lim turi (masalan, "Akademik", "Texnik")
}
