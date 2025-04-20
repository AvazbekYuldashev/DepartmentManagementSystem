package com.example.department_management_system.util;

import com.example.department_management_system.enums.ApplicationStatus;
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

    public void chekStatus(ApplicationStatus status){
        if (!(status.equals(ApplicationStatus.SENT) ||
                status.equals(ApplicationStatus.APPROVED) ||
                status.equals(ApplicationStatus.IN_PROGRESS) ||
                status.equals(ApplicationStatus.COMPLETED) ||
                status.equals(ApplicationStatus.REJECTED))) {
            throw new IllegalArgumentException("Invalid application status: " + status);}
    }

    // **Foydalanuvchi rolini tekshirish**
    public boolean hasPermissionToUpdate(EmployeeRole userRole, ApplicationStatus status) {
        return (userRole == EmployeeRole.SUPERADMIN && (status == ApplicationStatus.APPROVED || status == ApplicationStatus.REJECTED))
                || (userRole == EmployeeRole.ADMIN && (status == ApplicationStatus.IN_PROGRESS || status == ApplicationStatus.COMPLETED));
    }

    public void checkSuperAdminAccess() {
        if (!SpringSecurityUtil.getCurrentEmployeeRole().equals(EmployeeRole.SUPERADMIN.toString() )) {
            throw new AppBadRequestExeption("It does not belong to the current profile.");
        }
    }


}
