package com.example.department_management_system.repository.application;


import com.example.department_management_system.entity.ApplicationEntity;
import com.example.department_management_system.enums.ApplicationStatus;
import com.example.department_management_system.mapper.ApplicationMapper;
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
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {

    @Query("SELECT a.id as id, a.title as title, a.description as description, a.createdDate as createdDate, " +
            "a.updatedDate as updatedDate, o.id as offering, c.id as createdBy, " +
            "at.id as assignedTo, d.id as department, a.status as status " +
            "FROM ApplicationEntity a " +
            "LEFT JOIN a.offering o " +
            "LEFT JOIN a.createdBy c " +
            "LEFT JOIN a.assignedTo at " +
            "LEFT JOIN a.department d " +
            "WHERE a.visible = true order by a.createdDate desc")
    List<ApplicationMapper> findAllMapper();


    @Query("SELECT a.id as id, a.title as title, a.description as description, a.createdDate as createdDate, " +
            "a.updatedDate as updatedDate, o.id as offering, c.id as createdBy, " +
            "at.id as assignedTo, d.id as department, a.status as status " +
            "FROM ApplicationEntity a " +
            "LEFT JOIN a.offering o " +
            "LEFT JOIN a.createdBy c " +
            "LEFT JOIN a.assignedTo at " +
            "LEFT JOIN a.department d " +
            "where a.id = :idP and a.visible = true order by a.createdDate desc")
    Optional<ApplicationMapper> findByIdMapper(@Param("idP") Integer id);

    @Query("SELECT a.id as id, a.title as title, a.description as description, a.createdDate as createdDate, a.updatedDate as updatedDate, a.offering.id as offering, a.createdBy.id as createdBy, a.assignedTo.id as assignedTo, a.department.id as department, a.status as status " +
            "FROM ApplicationEntity a " +
            "LEFT JOIN a.offering o " +
            "LEFT JOIN a.createdBy c " +
            "LEFT JOIN a.assignedTo at " +
            "LEFT JOIN a.department d " +
            "WHERE c.id = :createdByIdP AND a.visible = true order by a.createdDate desc")
    List<ApplicationMapper> findByCreatedByMapper(@Param("createdByIdP") Integer createdById);

    @Query("SELECT a.id as id, a.title as title, a.description as description, a.createdDate as createdDate, a.updatedDate as updatedDate, a.offering.id as offering, a.createdBy.id as createdBy, a.assignedTo.id as assignedTo, a.department.id as department, a.status as status " +
            "FROM ApplicationEntity a " +
            "LEFT JOIN a.offering o " +
            "LEFT JOIN a.createdBy c " +
            "LEFT JOIN a.assignedTo at " +
            "LEFT JOIN a.department d " +
            "WHERE a.assignedTo.id = :assignedToIdP AND a.visible = true order by a.createdDate desc")
    List<ApplicationMapper> findByAssignedToMapper(@Param("assignedToIdP") Integer assignedToId);

    @Query("SELECT a.id as id, a.title as title, a.description as description, a.createdDate as createdDate, a.updatedDate as updatedDate, a.offering.id as offering, a.createdBy.id as createdBy, a.assignedTo.id as assignedTo, a.department.id as department, a.status as status " +
            "FROM ApplicationEntity a " +
            "LEFT JOIN a.offering o " +
            "LEFT JOIN a.createdBy c " +
            "LEFT JOIN a.assignedTo at " +
            "LEFT JOIN a.department d " +
            "WHERE a.department.id = :departmentIdP AND a.visible = true order by a.createdDate desc")
    List<ApplicationMapper> findByDepartmentMapper(@Param("departmentIdP") Integer departmentId);

    @Query("SELECT a.id as id, a.title as title, a.description as description, a.createdDate as createdDate, a.updatedDate as updatedDate, a.offering.id as offering, a.createdBy.id as createdBy, a.assignedTo.id as assignedTo, a.department.id as department, a.status as status " +
            "FROM ApplicationEntity a " +
            "LEFT JOIN a.offering o " +
            "LEFT JOIN a.createdBy c " +
            "LEFT JOIN a.assignedTo at " +
            "LEFT JOIN a.department d " +
            "WHERE a.status = :statusP AND a.visible = true order by a.createdDate desc")
    List<ApplicationMapper> findByStatusMapper(@Param("statusP") ApplicationStatus status);

    @Query("SELECT a.id as id, a.title as title, a.description as description, a.createdDate as createdDate, " +
            "a.updatedDate as updatedDate, o.id as offering, u.id as createdBy, e.id as assignedTo, d.id as department, " +
            "a.status as status " +
            "FROM ApplicationEntity a " +
            "LEFT JOIN a.offering o " +
            "LEFT JOIN a.createdBy u " +
            "LEFT JOIN a.assignedTo e " +
            "LEFT JOIN a.department d " +
            "WHERE a.visible = true order by a.createdDate desc")
    Page<ApplicationMapper> findAllPageble(Pageable pageable);

    @Query("SELECT a.id as id, a.title as title, a.description as description, a.createdDate as createdDate, " +
            "a.updatedDate as updatedDate, o.id as offering, u.id as createdBy, e.id as assignedTo, d.id as department, " +
            "a.status as status " +
            "FROM ApplicationEntity a " +
            "LEFT JOIN a.offering o " +
            "LEFT JOIN a.createdBy u " +
            "LEFT JOIN a.assignedTo e " +
            "LEFT JOIN a.department d " +
            "WHERE a.visible = true and a.createdBy.id = :idP order by a.createdDate desc  ")
    Page<ApplicationMapper> findCreatedByPaged(Pageable pageable, @Param("idP") Integer idP);

    @Modifying
    @Transactional
    @Query("UPDATE ApplicationEntity set assignedTo.id = :assignedToIdP, status = :statusP,  updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updateAssignedTo(@Param("idP") Integer id, @Param("assignedToIdP") Integer assignedToId, @Param("statusP") ApplicationStatus status, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE ApplicationEntity set status = :statusP, updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updateStatus(@Param("idP") Integer id, @Param("statusP") ApplicationStatus status, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE ApplicationEntity a " +
            "SET a.title = COALESCE(:titleP, a.title)," +
            "    a.description = COALESCE(:descriptionP, a.description), " +
            "    a.updatedDate = :updatedDateP " +
            " where a.id = :idP and a.visible = true ")
    int updateTitleAndDescription(@Param("idP") Integer id,
                                  @Param("titleP") String title,
                                  @Param("descriptionP") String description,
                                  @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE ApplicationEntity a " +
            "SET a.title = COALESCE(:titleP, a.title), " +
            "    a.description = COALESCE(:descriptionP, a.description), " +
            "    a.offering.id = COALESCE(:offeringIdP, a.offering.id), " +
            "    a.createdBy.id = COALESCE(:createdByP, a.createdBy.id), " +
            "    a.assignedTo.id = COALESCE(:assignedToIdP, a.assignedTo.id), " +
            "    a.department.id = COALESCE(:departmentIdP, a.department.id), " +
            "    a.status = COALESCE(:statusP, a.status), " +
            "    a.completedWork.id = COALESCE(:completedWorkIdP, a.completedWork.id), " +
            "    a.updatedDate = :updatedDateP " +
            "WHERE a.id = :idP AND a.visible = true")
    int updateApplication(
            @Param("idP") Integer id,
            @Param("titleP") String title,
            @Param("descriptionP") String description,
            @Param("offeringIdP") Integer offeringId,
            @Param("createdByP") Integer createdById,
            @Param("assignedToIdP") Integer assignedToId,
            @Param("departmentIdP") Integer departmentId,
            @Param("statusP") ApplicationStatus status,
            @Param("completedWorkIdP") Integer completedWorkId,
            @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE ApplicationEntity set visible = :visibleP, updatedDate = :updatedDateP where id = :idP")
    int updateVisible(@Param("idP") Integer id, @Param("visibleP") Boolean visible, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("DELETE ApplicationEntity where id = :idP")
    void deleteById(@Param("idP") Integer id);


}