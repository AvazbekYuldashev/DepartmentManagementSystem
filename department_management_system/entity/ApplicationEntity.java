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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private EmployeeEntity createdBy;       /// Arizani yuborgan xodim
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private EmployeeEntity assignedTo;      /// Arizani ko'rib chiqadigan xodim
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;    /// Ariza yuborilayotgan Bo'lim manzili
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offering_id")
    private OfferingEntity offering;        /// Ariza yuborilayotgan Xizmat ID
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;       /// Ariza xolati
    @OneToOne(mappedBy = "application")
    private CompletedWorkEntity completedWork;  /// Ariza bajarilganligi xaqida malumot
    @Column(name = "visible", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible;                /// korinishi
}