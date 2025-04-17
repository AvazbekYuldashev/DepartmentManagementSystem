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
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;      /// Yaratilgan sanasi
    @Column(name = "updated_date")          ///  Yangilangan sanasi
    private LocalDateTime updatedDate;
    @OneToOne(fetch = FetchType.LAZY)       /// Bajarilgan ishga tegishli ariza
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity application;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)     /// Bajarilgan ishga tegishli bo'lim
    private DepartmentEntity department;    /// Bo'lim
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)       /// Xizmat ko'rsatgan xodim
    private EmployeeEntity employee;        /// Xodim
    @Column(name = "visible", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible;                /// korinishi
}
