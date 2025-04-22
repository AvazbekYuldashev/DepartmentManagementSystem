package com.example.department_management_system.dto.application;


import com.example.department_management_system.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ApplicationFilterDTO {
    private Integer id;                     /// ID
    private String title;                   /// Ariza turi
    private String description;             /// Ariza matni
    private LocalDate createdDateFrom;      /// Ariza yaratilgan sanasi
    private LocalDate createdDateTo;        /// Ariza yaratilgan sanasi
    private LocalDate updatedDateFrom;      /// Ariza o'zgartirilgan sanasi
    private LocalDate updatedDateTo;        /// Ariza o'zgartirilgan sanasi
    private Integer offeringId;             /// Xizmat ID
    private Integer createdById;            /// Arizani yuborgan xodim ID
    private Integer assignedToId;           /// Arizani ko'rib chiqadigan xodim ID
    private Integer departmentId;           /// Ariza yuborilayotgan bo'lim ID
    private ApplicationStatus status;       /// Ariza xolati
    private Integer completedWorkId;        /// Ariza bajarilganligi xaqida ma'lumot
    private Boolean visible;
}
