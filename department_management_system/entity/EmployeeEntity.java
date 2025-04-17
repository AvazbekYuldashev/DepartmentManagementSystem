package com.example.department_management_system.entity;


import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


///  Xodimlar
@Getter
@Setter
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                                                 /// Id si
    @Column(name = "name", nullable = false)                            /// Ismi
    private String name;
    @Column(name = "surname", nullable = false)                         /// Familyasi
    private String surname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")                                              ///  Roli
    private EmployeeRole role = EmployeeRole.USER;
    @Column(name = "position")                                          /// Lavozimi
    private String position;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)                          /// Statusi (Blok yoki activ)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;                                  /// Yaratilgan sanasi
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;                                  /// Yangilangan sanasi
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")                                 /// Boglangan bo'lim
    private DepartmentEntity department;
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    private List<ApplicationEntity> applications;                       /// Xodimga tegishli bo'lgan arizalar
    @OneToMany(mappedBy = "assignedTo", fetch = FetchType.LAZY)
    private List<ApplicationEntity> assignedApplications;               /// Xodimga tayinlangan arizalar
    @Column(name = "visible", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible;                                            /// korinishi
}
