package com.example.department_management_system.entity;


import com.example.department_management_system.enums.DepartmentStatus;
import com.example.department_management_system.enums.DepartmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

///  Bolim
@Getter
@Setter
@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                                                   /// Id
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DepartmentStatus status;                                      /// Xozirgi xolati
    @Column(name = "title", nullable = false)
    private String title;                                                 /// Bo'lim nomi
    @Column(name = "description", length = 1000, nullable = false)        /// Bo'lim xaqida qisqacha
    private String description;
    @Column(name = "created_date", nullable = false)                      ///  Yaratilgan sanasi
    private LocalDateTime createdDate;
    @Column(name = "updated_date")                                        ///  Yangilangan sanasi
    private LocalDateTime updatedDate;
    @Column(name = "address", nullable = false)                           ///  Bo'lim elektron pochta manzili
    private String address;
    @Column(name = "phone_number")                                        /// Bo'lim telefon raqami
    private String phoneNumber;
    @Column(name = "head_of_department", nullable = false)                /// Bo'lim boshlig'ining ismi yoki ID-si.
    private String headOfDepartment;
    @Column(name = "type", nullable = false)                              /// Bo'lim turi (masalan, "Akademik", "Texnik").
    private DepartmentType type;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<EmployeeEntity> employees;                               /// Bo'lim tarkibidagi xodimlar
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<OfferingEntity> offerings;                               /// Bo'lim tarkibidagi xizmatlar (services)
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<ApplicationEntity> applications;                         /// Bo'limga kelgan arizalar
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<CompletedWorkEntity> completedWorks;                     /// Bo'limda bajarilgan ishalar
    @Column(name = "visible", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean visible = true;                                       /// korinishi
}
