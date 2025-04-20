package com.example.department_management_system.util;


import com.example.department_management_system.config.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpringSecurityUtil {

    public static Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {return null;}
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getId();
    }

    public static Collection<GrantedAuthority> getCurrentProfileGrantedAuthorityList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {return null;}
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) user.getAuthorities();
        return roles;
    }

    public static List<String> getCurrentProfileRolesList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {return null;}
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> roles = user.getAuthorities();
        List<String> rolesList = new ArrayList<String>();
        for (GrantedAuthority role : roles) {
            rolesList.add(role.getAuthority());
        }
        return rolesList;
    }

    public static Integer getCurrentDepartmentId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {return null;}
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getDepartmentId();
    }

    public static String getCurrentEmployeeRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {return null;}
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getRole();
    }


}
