package com.example.department_management_system.entity;


import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



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
    @Column(name = "position")                                          /// Lavozimi
    private String position;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;                                  /// Yaratilgan sanasi
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;                                  /// Yangilangan sanasi
    @Column(name = "department_id")
    private Integer departmentId;                                /// Boglangan bo'lim

    @Column(name = "visible", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible = true;         /// korinishi
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private GeneralStatus status = GeneralStatus.ACTIVE;  /// Statusi (Blok yoki activ)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private EmployeeRole role = EmployeeRole.USER;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private DepartmentEntity department;                                 /// Boglangan bo'lim


}
