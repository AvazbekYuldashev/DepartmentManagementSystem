package com.example.department_management_system.util;

import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.exp.AppBadRequestExeption;
import org.springframework.stereotype.Component;

@Component
public class CheckAccesss {

    public void checkAdminAccess() {
        String currentRole = SpringSecurityUtil.getCurrentEmployeeRole();
        if (!(currentRole.equals(EmployeeRole.ADMIN.toString()) || currentRole.equals(EmployeeRole.SUPERADMIN.toString()))) {
            throw new AppBadRequestExeption("It does not belong to the current profile.");
        }
    }
}
