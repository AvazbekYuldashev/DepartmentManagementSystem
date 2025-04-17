package com.example.department_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/// Xizmatlar
@Getter
@Setter
@Entity
@Table(name = "offering")
public class OfferingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                                     /// Id
    @Column(name = "status", nullable = false)
    private Boolean status = true ;                         /// Xolati (Ishlayabdi toxtatilgan)
    @Column(name = "title", nullable = false)
    private String title;                                   /// Nomi
    @Column(name = "description", nullable = false)
    private String description;                             /// Tavsifi
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;                      /// Yaratilgan sanasi
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;                      /// O'zgartirilgan sanasi
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;                    /// Xizmatga bog'langan bo'lim (department)
    @Column(name = "visible", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible;                                /// korinishi
}
