package com.example.department_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

///  Bajarilgan ishlar
@Entity
@Table(name = "completed_work")
@Getter
@Setter
public class CompletedWorkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                     /// ID
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;      /// Yaratilgan sanasi
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;      /// O'zgartirilgan sanasi
    @Column(name = "visible", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible;                /// Ko'rinishi

    @Column(name = "department_id", insertable = false, updatable = false)
    private Integer departmentId;              /// Bajarilgan ishga tegishli bo'lim
    @Column(name = "employee_id")
    private Integer employeeId;             /// Xizmat ko'rsatgan xodim
    @Column(name = "application_id", insertable = false, updatable = false)
    private Integer applicationId;          /// Bajarilgan ishga tegishli ariz
    ///
    @OneToOne(fetch = FetchType.LAZY)       /// Bajarilgan ishga tegishli ariza
    @JoinColumn(name = "application_id", insertable = false, updatable = false)
    private ApplicationEntity application;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private DepartmentEntity department;    /// Bajarilgan ishga tegishli bo'lim
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private EmployeeEntity employee;        /// Xizmat ko'rsatgan xodim
}
