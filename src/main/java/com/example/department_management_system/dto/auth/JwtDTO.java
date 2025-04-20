package com.example.department_management_system.dto.auth;

public class JwtDTO {
    private Integer id;                                                   /// Id si
    private String name;                                                  /// Ismi
    private String surname;                                               /// Familyasi
    private String role;                                                  /// Roli
    private String position;                                              /// Lavozimi
    private String status;                                        /// Statusi (Blok yoki aktiv)
    private Integer departmentId;                                         /// Bo'lim IDsi
    private Boolean visible;                                              /// korinishi
    private String email;

    public JwtDTO(Integer id, String name, String surname, String role, String position, String status, Integer departmentId, Boolean visible, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.position = position;
        this.status = status;
        this.departmentId = departmentId;
        this.visible = visible;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public String getEmail() {
        return email;
    }
}
