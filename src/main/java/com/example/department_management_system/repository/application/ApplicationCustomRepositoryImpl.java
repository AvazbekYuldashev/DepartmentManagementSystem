package com.example.department_management_system.repository.application;

import com.example.department_management_system.dto.application.ApplicationFilterDTO;
import com.example.department_management_system.entity.ApplicationEntity;
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
public class ApplicationCustomRepositoryImpl implements ApplicationCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageImpl<ApplicationEntity> filter(ApplicationFilterDTO dto, int page, int size) {
        StringBuilder query = new StringBuilder(" where a.visible = true ");
        Map<String, Object> params = new HashMap<>();
        if (dto.getId() != null) {
            query.append(" and a.id = :id ");
            params.put("id", dto.getId());
        }
        if (dto.getTitle() != null) {
            query.append(" and lower(a.title) like :title ");
            params.put("title", "%" + dto.getTitle() + "%");
        }
        if (dto.getDescription() != null) {
            query.append(" and lower(a.description) like :description ");
            params.put("description", "%" + dto.getDescription().toLowerCase() + "%");
        }
        if (dto.getCreatedDateTo() != null && dto.getCreatedDateFrom() != null) {
            query.append(" and a.createdDate between :createdDateFrom and :createdDateTo ");
            params.put("createdDateFrom", LocalDateTime.of(dto.getCreatedDateFrom(), LocalTime.MIN));
            params.put("createdDateTo", LocalDateTime.of(dto.getCreatedDateTo(), LocalTime.MAX));
        } else if (dto.getCreatedDateFrom() != null) {
            query.append(" and a.createdDate >= :createdDateFrom ");
            params.put("createdDateFrom", LocalDateTime.of(dto.getCreatedDateFrom(), LocalTime.MIN));
        } else if (dto.getCreatedDateTo() != null) {
            query.append(" and a.createdDate <= :createdDateTo ");
            params.put("createdDateTo", LocalDateTime.of(dto.getCreatedDateTo(), LocalTime.MAX));
        }
        if (dto.getUpdatedDateTo() != null && dto.getUpdatedDateFrom() != null) {
            query.append(" and a.updatedDate between :updatedDateFrom and :updatedDateTo ");
            params.put("updatedDateFrom", LocalDateTime.of(dto.getUpdatedDateFrom(), LocalTime.MIN));
            params.put("updatedDateTo", LocalDateTime.of(dto.getUpdatedDateTo(), LocalTime.MAX));
        } else if (dto.getUpdatedDateFrom() != null) {
            query.append(" and a.updatedDate >= :updatedDateFrom ");
            params.put("updatedDateFrom", LocalDateTime.of(dto.getUpdatedDateFrom(), LocalTime.MIN));
        } else if (dto.getUpdatedDateTo() != null) {
            query.append(" and a.updatedDate <= :updatedDateTo ");
            params.put("updatedDateTo", LocalDateTime.of(dto.getUpdatedDateTo(), LocalTime.MAX));
        }
        if (dto.getOfferingId() != null) {
            query.append(" and a.offering.id = :offeringId ");
            params.put("offeringId", dto.getOfferingId());
        }
        if (dto.getCreatedById() != null) {
            query.append(" and a.createdBy.id = :createdById ");
            params.put("createdById", dto.getCreatedById());
        }
        if (dto.getAssignedToId() != null) {
            query.append(" and a.assignedTo.id = :assignedToId ");
            params.put("assignedToId", dto.getAssignedToId());
        }
        if (dto.getDepartmentId() != null) {
            query.append(" and a.department.id = :departmentId ");
            params.put("departmentId", dto.getDepartmentId());
        }
        if (dto.getStatus() != null) {
            query.append(" and a.status = :status ");
            params.put("status", dto.getStatus());
        }
        if (dto.getCompletedWorkId() != null) {
            query.append(" and a.completedWork.id = :completedWorkId ");
            params.put("completedWorkId", dto.getCompletedWorkId());
        }

        StringBuilder selectBuilder = new StringBuilder("select a from ApplicationEntity a ");
        selectBuilder.append(query);
        selectBuilder.append(" order by a.createdDate desc");

        StringBuilder countBuilder = new StringBuilder("select count(a) from ApplicationEntity a ");
        countBuilder.append(query);

        ///  Content
        Query selectQuery = entityManager.createQuery(selectBuilder.toString(), ApplicationEntity.class);
        params.forEach(selectQuery::setParameter);
        selectQuery.setFirstResult(page * size);
        selectQuery.setMaxResults(size);  // limit size
        List<ApplicationEntity> content = selectQuery.getResultList();

        ///  Tootal Count
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        params.forEach(countQuery::setParameter);
        Long totalCount = (Long) countQuery.getSingleResult();

        return new PageImpl<>(content, PageRequest.of(page, size), totalCount);
    }
}
