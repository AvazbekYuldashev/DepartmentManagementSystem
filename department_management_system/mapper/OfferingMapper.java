package com.example.department_management_system.mapper;

import java.time.LocalDateTime;

public interface OfferingMapper {
    Integer getId();                                       /// Id
    Boolean getStatus();                                   /// Xolati (Ishlayabdi yoki to'xtatilgan)
    String getTitle();                                     /// Xizmat nomi
    String getDescription();                               /// Tavsifi
    LocalDateTime getCreatedDate();                          /// Yaratilgan sanasi
    LocalDateTime getUpdatedDate();                          /// O'zgartirilgan sanasi
    Integer getDepartmentId();                               /// Bo'lim IDsi
}
