package com.example.department_management_system.dto.completedWork;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

///  Bajarilgan ishlar
@Getter
@Setter
public class CompletedWorkFilterDTO {
    private Integer id;                         /// ID
    private LocalDate createdDateFrom;          /// Yaratilgan sanasi boshi
    private LocalDate createdDateTo;            /// Yaratilgan sanasi oxiri
    private LocalDate updatedDateFrom;          /// Yangilangan sanasi boshi
    private LocalDate updatedDateTo;            /// Yangilangan sanasi oxiri
    private Integer applicationId;              /// Bajarilgan ishga tegishli ariza ID
    private Integer departmentId;               /// Bajarilgan ishga tegishli bo'lim ID
    private Integer employeeId;                 /// Xizmat ko'rsatgan xodim ID
    private Boolean visible;
}