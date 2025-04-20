package com.example.department_management_system.dto.application;


import com.example.department_management_system.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/// Arizalar
@Getter
@Setter
public class ApplicationDTO {

    private Integer id;                     /// ID
    @NotBlank(message = "Title required")
    private String title;                   /// Ariza turi
    @NotBlank(message = "Description required")
    private String description;             /// Ariza matni
    private LocalDateTime createdDate;      /// Ariza yaratilgan sanasi
    private LocalDateTime updatedDate;      /// Ariza o'zgartirilgan sanasi
    @NotNull(message = "Offering Id required")
    private Integer offeringId;             /// Xizmat ID
    private Integer createdById;            /// Arizani yuborgan xodim ID
    private Integer assignedToId;           /// Arizani ko'rib chiqadigan xodim ID
    private Integer departmentId;           /// Ariza yuborilayotgan bo'lim ID
    private ApplicationStatus status;       /// Ariza xolati
    private Integer completedWorkId;        /// Ariza bajarilganligi xaqida ma'lumot
    private Boolean visible;

}
