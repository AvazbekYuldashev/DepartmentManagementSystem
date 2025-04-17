package com.example.department_management_system.repository.employee;

import com.example.department_management_system.entity.EmployeeEntity;
import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.enums.EmployeeStatus;
import com.example.department_management_system.mapper.EmployeeMapper;
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
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Query("SELECT e.id as id, e.name as name, e.surname as surname, e.role as role, " +
            "e.position as position, e.status as status, e.createdDate as createdDate, " +
            "e.updatedDate as updatedDate, e.email as email, e.password as password, " +
            "d.id as department, d.title as departmentTitle, e.visible as visible " +
            "FROM EmployeeEntity e LEFT JOIN e.department d " +
            "WHERE e.visible = true order by e.createdDate desc")
    List<EmployeeMapper> findAllMapper();

    @Query("SELECT e.id as id, e.name as name, e.surname as surname, e.role as role, " +
            "e.position as position, e.status as status, e.createdDate as createdDate, " +
            "e.updatedDate as updatedDate, e.department.id as department, e.email as email, " +
            "e.password as password, e.department.title as departmentTitle, e.visible as visible " +
            "FROM EmployeeEntity e LEFT JOIN e.department d " +
            "WHERE e.id = :idP AND e.visible = true order by e.createdDate desc")
    Optional<EmployeeMapper> findByIdMapper(@Param("idP") Integer id);

    @Query("SELECT e.id as id, e.name as name, e.surname as surname, e.role as role, " +
            "e.position as position, e.status as status, e.createdDate as createdDate, " +
            "e.updatedDate as updatedDate, e.department.id as department, e.email as email, " +
            "e.password as password, e.department.title as departmentTitle, e.visible as visible " +
            "FROM EmployeeEntity e LEFT JOIN e.department d " +
            "WHERE e.department.id = :departmentIdP and e.visible = true order by e.createdDate desc")
    List<EmployeeMapper> findByDepartmentMapper(@Param("departmentIdP") Integer departmentId);

    @Query("SELECT e.id as id, e.name as name, e.surname as surname, e.role as role, e.position as position, " +
            "e.status as status, e.createdDate as createdDate, e.updatedDate as updatedDate, d.id as department, " +
            "e.email as email, e.password as password, e.visible as visible  " +
            "FROM EmployeeEntity e LEFT JOIN e.department d " +
            "WHERE e.visible = true order by e.createdDate desc")
    Page<EmployeeMapper> findAllPageble(Pageable pageable);

    @Query("FROM EmployeeEntity where email = :emailP and visible = true order by createdDate desc")
    Optional<EmployeeEntity> findByEmailAndVisibleTrue(@Param("emailP") String email);


    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity set role = :roleP, updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updateRole(@Param("idP") Integer id, @Param("roleP") EmployeeRole role, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity set position = :positionP, updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updatePosition(@Param("idP") Integer id, @Param("positionP") String position, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity set status = :statusP, updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updateStatus(@Param("idP") Integer id, @Param("statusP") EmployeeStatus status, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity set department.id = :departmentIdP, updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updateDepartment(@Param("idP") Integer id, @Param("departmentIdP") Integer departmentId, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity e " +
            " SET e.name = COALESCE(:nameP, e.name), " +
            "    e.surname = COALESCE(:surnameP, e.surname), " +
            "    e.role = COALESCE(:roleP, e.role), " +
            "    e.position = COALESCE(:positionP, e.position), " +
            "    e.status = COALESCE(:statusP, e.status), " +
            "    e.department.id = COALESCE(:departmentIdP, e.department.id), " +
            "    e.email = COALESCE(:emailP, e.email), " +
            "    e.password = COALESCE(:passwordP, e.password), " +
            "    e.updatedDate = :updatedDateP " +
            "WHERE e.id = :idP AND e.visible = true")
    int updateEmployee(
            @Param("idP") Integer id,
            @Param("nameP") String name,
            @Param("surnameP") String surname,
            @Param("roleP") EmployeeRole role,
            @Param("positionP") String position,
            @Param("statusP") EmployeeStatus status,
            @Param("departmentIdP") Integer departmentId,
            @Param("emailP") String email,
            @Param("passwordP") String password,
            @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity set visible = :visibleP, updatedDate = :updatedDateP where id = :idP")
    int updateVisible(@Param("idP") Integer id, @Param("visibleP") Boolean visible, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("DELETE EmployeeEntity where id = :idP")
    void deleteById(@Param("idP") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity e " +
            " SET e.name = COALESCE(:nameP, e.name), " +
            "    e.surname = COALESCE(:surnameP, e.surname), " +
            "    e.email = COALESCE(:emailP, e.email), " +
            "    e.password = COALESCE(:passwordP, e.password), " +
            "    e.updatedDate = :updatedDateP " +
            "WHERE e.id = :idP AND e.visible = true")
    int updateCurrentUser(
            @Param("idP") Integer id,
            @Param("nameP") String name,
            @Param("surnameP") String surname,
            @Param("emailP") String email,
            @Param("passwordP") String password,
            @Param("updatedDateP") LocalDateTime updatedDateP);
}
