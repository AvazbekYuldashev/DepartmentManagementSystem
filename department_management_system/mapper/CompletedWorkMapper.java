package com.example.department_management_system.mapper;

import java.time.LocalDateTime;

public interface CompletedWorkMapper {
    Integer getId();                     /// ID
    LocalDateTime getCreatedDate();      /// Yaratilgan sanasi
    LocalDateTime getUpdatedDate();      /// Yangilangan sanasi
    Integer getApplicationId();          /// Bajarilgan ishga tegishli ariza ID
    Integer getDepartmentId();           /// Bajarilgan ishga tegishli bo'lim ID
    Integer getEmployeeId();             /// Xizmat ko'rsatgan xodim ID

}
