package com.example.department_management_system.entity;

import com.example.department_management_system.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/// Arizalar
@Getter
@Setter
@Entity
@Table(name = "application")
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                     /// ID
    @Column(name = "title", nullable = false)
    private String title;                   /// Ariza turi

    @Column(name = "description", nullable = false)
    private String description;             /// Ariza matni
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;      /// Ariza yaratilgan sanasi
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;      /// Ariza o'zgartirilgan sanasi
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;       /// Ariza xolati
    @Column(name = "visible", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible;                /// korinishi

    @Column(name = "created_by", nullable = false)
    private Integer createdById; // Arizani yuborgan xodimning ID si
    @Column(name = "assigned_to", nullable = false)
    private Integer assignedToId; // Arizani ko'rib chiqadigan xodimning ID si
    @Column(name = "department_id", nullable = false)
    private Integer departmentId;    /// Ariza yuborilayotgan Bo'lim manzili
    @Column(name = "offering_id")
    private Integer offeringId;        /// Ariza yuborilayotgan Xizmat ID
    @Column(name = "completed_work_id")
    private Integer completedWorkId;        /// Ariza bajarilgan xisobot ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    private EmployeeEntity createdBy; // Arizani yuborgan xodim
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to", referencedColumnName = "id", insertable = false, updatable = false)
    private EmployeeEntity assignedTo; // Arizani ko'rib chiqadigan xodim
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DepartmentEntity department;    /// Ariza yuborilayotgan Bo'lim manzili
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offering_id", referencedColumnName = "id", insertable = false, updatable = false)
    private OfferingEntity offering;        /// Ariza yuborilayotgan Xizmat ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "completed_work_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CompletedWorkEntity completedWork; // Bajarilgan ish



}
