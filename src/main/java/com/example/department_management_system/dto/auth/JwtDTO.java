package com.example.department_management_system.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {
    private Integer id;               /// Id si
    private String name;              /// Ismi
    private String surname;           /// Familyasi
    private String role;              /// Roli
    private String position;          /// Lavozimi
    private String status;            /// Statusi (Blok yoki aktiv)
    private Integer departmentId;     /// Bo'lim IDsi
    private Boolean visible;          /// korinishi
    private String email;             /// Email
}
