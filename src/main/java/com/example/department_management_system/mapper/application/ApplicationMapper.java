package com.example.department_management_system.mapper.application;



import com.example.department_management_system.enums.ApplicationStatus;

import java.time.LocalDateTime;

public interface ApplicationMapper {
    Integer getId();                     /// ID
    String getTitle();                   /// Ariza turi
    String getDescription();             /// Ariza matni
    LocalDateTime getCreatedDate();      /// Ariza yaratilgan sanasi
    LocalDateTime getUpdatedDate();      /// Ariza o'zgartirilgan sanasi
    Integer getOffering();               /// Xizmat ID
    Integer getCreatedBy();              /// Arizani yuborgan xodim ID
    Integer getAssignedTo();             /// Arizani ko'rib chiqadigan xodim ID
    Integer getDepartment();             /// Ariza yuborilayotgan bo'lim ID
    ApplicationStatus getStatus();       /// Ariza xolati
}
