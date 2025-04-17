package com.example.department_management_system.repository.department;


import com.example.department_management_system.entity.DepartmentEntity;
import com.example.department_management_system.enums.DepartmentStatus;
import com.example.department_management_system.enums.DepartmentType;
import com.example.department_management_system.mapper.DepartmentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {


    DepartmentEntity save(DepartmentEntity entity);

    @Query("SELECT d.id as id, d.status as status, d.title as title, d.description as description, d.createdDate as createdDate, d.updatedDate as updatedDate, d.address as address, d.phoneNumber as phoneNumber, d.headOfDepartment as headOfDepartment, d.type as type FROM DepartmentEntity d where d.visible = true order by d.createdDate desc")
    List<DepartmentMapper> findAllMapper();

    @Query("SELECT d.id as id, d.status as status, d.title as title, d.description as description, d.createdDate as createdDate, d.updatedDate as updatedDate, d.address as address, d.phoneNumber as phoneNumber, d.headOfDepartment as headOfDepartment, d.type as type from DepartmentEntity d where d.id = :idP and d.visible = true order by d.createdDate desc")
    Optional<DepartmentMapper> getByIdMapper(@Param("idP") Integer id);

    @Query("SELECT d.id as id, d.status as status, d.title as title, d.description as description, d.createdDate as createdDate, d.updatedDate as updatedDate, d.address as address, d.phoneNumber as phoneNumber, d.headOfDepartment as headOfDepartment, d.type as type FROM DepartmentEntity d where d.visible = true order by d.createdDate desc")
    Page<DepartmentMapper> findAllPageble(Pageable pageable);

    @Query("FROM DepartmentEntity WHERE id = :idP order by createdDate desc")
    Optional<DepartmentEntity> findByIdCustom(@Param("idP") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE DepartmentEntity set status = :statusP, updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updateStatus(@Param("idP") Integer id, @Param("statusP") DepartmentStatus status, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE DepartmentEntity d " +
            " SET d.status = COALESCE(:statusP, d.status), " +
            "    d.title = COALESCE(:titleP, d.title), " +
            "    d.description = COALESCE(:descriptionP, d.description), " +
            "    d.address = COALESCE(:addressP, d.address), " +
            "    d.phoneNumber = COALESCE(:phoneNumberP, d.phoneNumber), " +
            "    d.headOfDepartment = COALESCE(:headOfDepartmentP, d.headOfDepartment), " +
            "    d.type = COALESCE(:typeP, d.type), " +
            "    d.updatedDate = :updatedDateP " +
            "WHERE d.id = :idP and d.visible = true ")
    int updateDepartment(
            @Param("idP") Integer id,
            @Param("statusP") DepartmentStatus status,
            @Param("titleP") String title,
            @Param("descriptionP") String description,
            @Param("addressP") String address,
            @Param("phoneNumberP") String phoneNumber,
            @Param("headOfDepartmentP") String headOfDepartment,
            @Param("typeP") DepartmentType type,
            @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE DepartmentEntity set visible = :visibleP, updatedDate = :updatedDateP where id = :idP")
    int updateVisible(@Param("idP") Integer id, @Param("visibleP") Boolean visible, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("DELETE DepartmentEntity where id = :idP")
    void deleteById(@Param("idP") Integer id);



}
