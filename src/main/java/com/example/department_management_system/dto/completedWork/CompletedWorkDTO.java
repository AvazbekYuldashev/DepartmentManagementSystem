package com.example.department_management_system.dto.completedWork;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

///  Bajarilgan ishlar
@Getter
@Setter
public class CompletedWorkDTO {
    private Integer id;                     /// ID
    private LocalDateTime createdDate;      /// Yaratilgan sanasi
    private LocalDateTime updatedDate;      /// Yangilangan sanasi
    @NotNull(message = "Application Id required")
    private Integer applicationId;          /// Bajarilgan ishga tegishli ariza ID
    @NotNull(message = "Department Id required")
    private Integer departmentId;           /// Bajarilgan ishga tegishli bo'lim ID
    @NotNull(message = "Employee required")
    private Integer employeeId;             /// Xizmat ko'rsatgan xodim ID
    private Boolean visible;
}