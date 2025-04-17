package com.example.department_management_system.config;

import com.example.department_management_system.entity.EmployeeEntity;
import com.example.department_management_system.enums.EmployeeStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;
    private String position;
    private String status;
    private Integer departmentId;
    private Boolean visible;

    public CustomUserDetails(EmployeeEntity profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.surname = profile.getSurname();
        this.email = profile.getEmail();
        this.password = profile.getPassword();
        this.role = profile.getRole().toString();
        this.position = profile.getPosition();
        this.status = profile.getStatus().toString();
        if (profile.getDepartment() != null) {
            this.departmentId = profile.getDepartment().getId();
        }else{
            this.departmentId = null;
        }
        this.visible = profile.getVisible();

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status == EmployeeStatus.ACTIVE.toString();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return visible;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getVisible() {
        return visible;
    }
}
