package com.example.department_management_system.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {
    private String name;
    private String email;
    private String password;
    private String jwt;
}
