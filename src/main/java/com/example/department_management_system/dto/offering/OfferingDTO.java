package com.example.department_management_system.dto.offering;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/// Xizmatlar
@Getter
@Setter
public class OfferingDTO {
    private Integer id;                                         /// Id
    private Boolean status;                                     /// Xolati (Ishlayabdi yoki to'xtatilgan)
    @NotBlank(message = "Title required")
    private String title;                                       /// Xizmat nomi
    @NotBlank(message = "Descriotion required")
    private String description;                                 /// Tavsifi
    private LocalDateTime createdDate;                          /// Yaratilgan sanasi
    private LocalDateTime updatedDate;                          /// O'zgartirilgan sanasi
    @NotNull(message = "Department Id required")
    private Integer departmentId;                               /// Bo'lim IDsi
    private List<Integer> applicationIds;                       /// Xizmatga bog'langan arizalar IDlari
    private Boolean visible;
}
