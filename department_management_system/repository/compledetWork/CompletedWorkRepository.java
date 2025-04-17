package com.example.department_management_system.repository.compledetWork;

import com.example.department_management_system.entity.CompletedWorkEntity;
import com.example.department_management_system.mapper.CompletedWorkMapper;
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
public interface CompletedWorkRepository extends JpaRepository<CompletedWorkEntity, Integer> {

    @Query("SELECT c.id as id, c.createdDate as createdDate, c.updatedDate as updatedDate, c.application.id as applicationId, c.department.id as departmentId, c.employee.id as employeeId " +
            "FROM CompletedWorkEntity c " +
            "LEFT JOIN c.application a " +
            "LEFT JOIN c.department d " +
            "LEFT JOIN c.employee e " +
            "WHERE c.visible = true order by c.createdDate desc")
    List<CompletedWorkMapper> findAllMapper();

    @Query("SELECT c.id as id, c.createdDate as createdDate, c.updatedDate as updatedDate, c.application.id as applicationId, c.department.id as departmentId, c.employee.id as employeeId " +
            "FROM CompletedWorkEntity c " +
            "LEFT JOIN c.application a " +
            "LEFT JOIN c.department d " +
            "LEFT JOIN c.employee e " +
            "WHERE c.id = :idP AND c.visible = true order by c.createdDate desc")
    Optional<CompletedWorkMapper> findByIdMapper(@Param("idP") Integer id);

    @Query("SELECT c.id as id, c.createdDate as createdDate, c.updatedDate as updatedDate, c.application.id as applicationId, c.department.id as departmentId, c.employee.id as employeeId " +
            "FROM CompletedWorkEntity c " +
            "LEFT JOIN c.application a " +
            "LEFT JOIN c.department d " +
            "LEFT JOIN c.employee e " +
            "WHERE c.application.id = :applicationIdP AND c.visible = true order by c.createdDate desc")
    List<CompletedWorkMapper> findByApplicationMapper(@Param("applicationIdP") Integer applicationId);

    @Query("SELECT c.id as id, c.createdDate as createdDate, c.updatedDate as updatedDate, c.application.id as applicationId, c.department.id as departmentId, c.employee.id as employeeId " +
            "FROM CompletedWorkEntity c " +
            "LEFT JOIN c.application a " +
            "LEFT JOIN c.department d " +
            "LEFT JOIN c.employee e " +
            "WHERE c.department.id = :departmentIdP AND c.visible = true order by c.createdDate desc")
    List<CompletedWorkMapper> findByDepartmentIdMapper(@Param("departmentIdP") Integer departmentId);

    @Query("SELECT c.id as id, c.createdDate as createdDate, c.updatedDate as updatedDate, c.application.id as applicationId, c.department.id as departmentId, c.employee.id as employeeId " +
            "FROM CompletedWorkEntity c " +
            "LEFT JOIN c.application a " +
            "LEFT JOIN c.department d " +
            "LEFT JOIN c.employee e " +
            "WHERE c.employee.id = :employeeIdP AND c.visible = true order by c.createdDate desc")
    List<CompletedWorkMapper> findByEmployeeIdMapper(@Param("employeeIdP") Integer employeeId);

    @Query("SELECT c.id as id, c.createdDate as createdDate, c.updatedDate as updatedDate, a.id as applicationId, " +
            "d.id as departmentId, e.id as employeeId " +
            "FROM CompletedWorkEntity c " +
            "LEFT JOIN c.application a " +
            "LEFT JOIN c.department d " +
            "LEFT JOIN c.employee e " +
            "WHERE c.visible = true order by c.createdDate desc")
    Page<CompletedWorkMapper> findAllPageble(Pageable pageable);

    @Query("FROM CompletedWorkEntity WHERE id = :idP AND visible = true order by createdDate desc")
    Optional<CompletedWorkEntity> findById(@Param("idP") Integer id);


    @Modifying
    @Transactional
    @Query("UPDATE CompletedWorkEntity set employee.id = :employeeP, updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updateEmployee(@Param("idP") Integer id, @Param("employeeP") Integer employeeId, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE CompletedWorkEntity c " +
            "SET c.application.id = COALESCE(:applicationIdP, c.application.id), " +
            "    c.department.id = COALESCE(:departmentIdP, c.department.id), " +
            "    c.employee.id = COALESCE(:employeeP, c.employee.id), " +
            "    c.updatedDate = :updatedDateP " +
            "WHERE c.id = :idP AND c.visible = true")
    int updateCompletedWork(
            @Param("idP") Integer id,
            @Param("applicationIdP") Integer applicationId,
            @Param("departmentIdP") Integer departmentId,
            @Param("employeeP") Integer employeeId,
            @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE CompletedWorkEntity set visible = :visibleP, updatedDate = :updatedDateP where id = :idP")
    int updateVisible(@Param("idP") Integer id, @Param("visibleP") Boolean visible, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("DELETE CompletedWorkEntity where id = :idP")
    void deleteById(@Param("idP") Integer id);


}