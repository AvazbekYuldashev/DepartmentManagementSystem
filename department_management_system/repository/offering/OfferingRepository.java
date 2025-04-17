package com.example.department_management_system.repository.offering;


import com.example.department_management_system.entity.OfferingEntity;
import com.example.department_management_system.mapper.OfferingMapper;
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
public interface OfferingRepository extends JpaRepository<OfferingEntity, Integer> {

    @Query("SELECT o.id as id, o.status as status, o.title as title, o.description as description, " +
            "o.createdDate as createdDate, o.updatedDate as updatedDate, d.id as departmentId " +
            "FROM OfferingEntity o LEFT JOIN o.department d WHERE o.visible = true order by o.createdDate desc")
    List<OfferingMapper> findAllMapper();

    @Query("SELECT o.id as id, o.status as status, o.title as title, o.description as description, " +
            "o.createdDate as createdDate, o.updatedDate as updatedDate, d.id as departmentId " +
            "FROM OfferingEntity o LEFT JOIN o.department d WHERE o.id = :idP AND o.visible = true order by o.createdDate desc")
    Optional<OfferingMapper> findByIdMapper(@Param("idP") Integer id);

    @Query("SELECT o.id as id, o.status as status, o.title as title, o.description as description, " +
            "o.createdDate as createdDate, o.updatedDate as updatedDate, d.id as departmentId " +
            "FROM OfferingEntity o LEFT JOIN o.department d WHERE o.department.id = :idP AND o.visible = true order by o.createdDate desc")
    List<OfferingMapper> findByDepartmentMapper(@Param("idP") Integer id);


    @Modifying
    @Transactional
    @Query("UPDATE OfferingEntity set status = :statusP, updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updateStatus(@Param("idP") Integer id, @Param("statusP") Boolean status, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE OfferingEntity set department.id = :departmentIdP, updatedDate = :updatedDateP where id = :idP and visible = true ")
    int updateDepartmentIdP(@Param("idP") Integer id, @Param("departmentIdP") Integer departmentId, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE OfferingEntity o " +
            " SET o.status = COALESCE(:statusP, o.status), " +
            "     o.title = COALESCE(:titleP, o.title), " +
            "     o.description = COALESCE(:descriptionP, o.description), " +
            "     o.department.id = COALESCE(:departmentIdP, o.department.id), " +
            "     o.updatedDate = :updatedDateP " +
            "WHERE o.id = :idP AND o.visible = true")
    int updateOffering(
            @Param("idP") Integer id,
            @Param("statusP") Boolean status,
            @Param("titleP") String title,
            @Param("descriptionP") String description,
            @Param("departmentIdP") Integer departmentId,
            @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("UPDATE OfferingEntity set visible = :visibleP, updatedDate = :updatedDateP where id = :idP")
    int updateVisible(@Param("idP") Integer id, @Param("visibleP") Boolean visible, @Param("updatedDateP") LocalDateTime updatedDateP);

    @Modifying
    @Transactional
    @Query("DELETE OfferingEntity where id = :idP")
    void deleteById(@Param("idP") Integer id);

    @Query("SELECT o.id as id, o.status as status, o.title as title, o.description as description, o.createdDate as createdDate, " +
            "o.updatedDate as updatedDate, d.id as departmentId " +
            "FROM OfferingEntity o LEFT JOIN o.department d WHERE o.visible = true order by o.createdDate desc")
    Page<OfferingMapper> findAllPageble(Pageable pageable);

    @Query("SELECT o.id as id, o.status as status, o.title as title, o.description as description, o.createdDate as createdDate, " +
            "o.updatedDate as updatedDate, d.id as departmentId " +
            "FROM OfferingEntity o LEFT JOIN o.department d WHERE o.visible = true and d.id = :departmentId order by o.createdDate desc")
    Page<OfferingMapper> findAllByDepartmentIdPageble(Pageable pageable, @Param("departmentId") Integer departmentId);

}
