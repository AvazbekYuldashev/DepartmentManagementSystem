package com.example.department_management_system.repository.compledetWork;

import com.example.department_management_system.dto.completedWork.CompletedWorkFilterDTO;
import com.example.department_management_system.entity.CompletedWorkEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CompletedWorkCustomRepositoryImpl implements CompletedWorkCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageImpl<CompletedWorkEntity> filter(CompletedWorkFilterDTO completedWorkFilterDTO, int page, int size) {
        StringBuilder query = new StringBuilder(" where c.visible = true ");
        Map<String, Object> params = new HashMap<>();

        if (completedWorkFilterDTO.getId() != null) {
            query.append(" and c.id = :id");
            params.put("id", completedWorkFilterDTO.getId());
        }
        if (completedWorkFilterDTO.getApplicationId() != null) {
            query.append(" and c.application.id = :applicationId ");
            params.put("applicationId", completedWorkFilterDTO.getApplicationId());
        }
        if (completedWorkFilterDTO.getDepartmentId() != null) {
            query.append(" and c.department.id = :departmentId ");
            params.put("departmentId", completedWorkFilterDTO.getDepartmentId());
        }
        if (completedWorkFilterDTO.getEmployeeId() != null) {
            query.append(" and c.employee.id = :employeeId ");
            params.put("employeeId", completedWorkFilterDTO.getEmployeeId());
        }

        if (completedWorkFilterDTO.getCreatedDateTo() != null && completedWorkFilterDTO.getCreatedDateFrom() != null) {
            query.append(" and c.createdDate between :createdDateFrom and :createdDateTo ");
            params.put("createdDateFrom", LocalDateTime.of(completedWorkFilterDTO.getCreatedDateFrom(), LocalTime.MIN) );
            params.put("createdDateTo", LocalDateTime.of(completedWorkFilterDTO.getCreatedDateTo(), LocalTime.MAX) );
        } else if (completedWorkFilterDTO.getCreatedDateFrom() != null) {
            query.append(" and c.createdDate >= :createdDateFrom ");
            params.put("createdDateFrom", LocalDateTime.of(completedWorkFilterDTO.getCreatedDateFrom(), LocalTime.MIN) );
        } else if (completedWorkFilterDTO.getCreatedDateTo() != null) {
            query.append(" and c.createdDate <= :createdDateTo ");
            params.put("createdDateTo", LocalDateTime.of(completedWorkFilterDTO.getCreatedDateTo(), LocalTime.MAX) );
        }

        if (completedWorkFilterDTO.getUpdatedDateTo() != null && completedWorkFilterDTO.getUpdatedDateFrom() != null) {
            query.append(" and c.updatedDate between :updatedDateFrom and :updatedDateTo ");
            params.put("updatedDateFrom", LocalDateTime.of(completedWorkFilterDTO.getUpdatedDateFrom(), LocalTime.MIN));
            params.put("updatedDateTo", LocalDateTime.of(completedWorkFilterDTO.getUpdatedDateTo(), LocalTime.MAX) );
        } else if (completedWorkFilterDTO.getUpdatedDateFrom() != null) {
            query.append(" and c.updatedDate >= :updatedDateFrom ");
            params.put("updatedDateFrom", LocalDateTime.of(completedWorkFilterDTO.getUpdatedDateFrom(), LocalTime.MIN));
        } else if (completedWorkFilterDTO.getUpdatedDateTo() != null) {
            query.append(" and c.updatedDate <= :updatedDateTo ");
            params.put("updatedDateTo", LocalDateTime.of(completedWorkFilterDTO.getUpdatedDateTo(), LocalTime.MAX) );
        }

        StringBuilder selectBuilder = new StringBuilder("select c from CompletedWorkEntity c ");
        selectBuilder.append(query);
        selectBuilder.append(" order by c.createdDate desc");

        StringBuilder countBuilder = new StringBuilder("select count(c) from CompletedWorkEntity c ");
        countBuilder.append(query);

        ///  Content
        Query selectQuery = entityManager.createQuery(selectBuilder.toString(), CompletedWorkEntity.class);
        params.forEach(selectQuery::setParameter);
        selectQuery.setFirstResult(page * size);
        selectQuery.setMaxResults(size);  // limit size
        List<CompletedWorkEntity> content = selectQuery.getResultList();

        ///  Tootal Count
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        params.forEach(countQuery::setParameter);
        Long totalCount = (Long) countQuery.getSingleResult();

        return new PageImpl<>(content, PageRequest.of(page, size), totalCount);
    }
}
