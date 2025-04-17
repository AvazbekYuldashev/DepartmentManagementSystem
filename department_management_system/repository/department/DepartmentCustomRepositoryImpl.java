package com.example.department_management_system.repository.department;

import com.example.department_management_system.dto.department.DepartmentFilterDTO;
import com.example.department_management_system.entity.DepartmentEntity;
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
public class DepartmentCustomRepositoryImpl implements DepartmentCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageImpl<DepartmentEntity> filter(DepartmentFilterDTO dto, int page, int size) {
        StringBuilder query = new StringBuilder(" where d.visible = true ");
        Map<String, Object> params = new HashMap<>();

        if (dto.getId() != null) {
            query.append(" and d.id = :id ");
            params.put("id", dto.getId());
        }
        if (dto.getStatus() != null) {
            query.append(" and d.status = :status ");
            params.put("status", dto.getStatus());
        }
        if (dto.getTitle() != null) {
            query.append(" and lower(d.title) like :title ");
            params.put("title", "%" + dto.getTitle().toLowerCase() + "%");
        }
        if (dto.getDescription() != null) {
            query.append(" and lower(d.description) like :description ");
            params.put("description", "%" + dto.getDescription().toLowerCase() + "%");
        }
        if (dto.getAddress() != null) {
            query.append(" and lower(d.address) like :address ");
            params.put("address", "%" + dto.getAddress().toLowerCase() + "%");
        }
        if (dto.getPhoneNumber() != null) {
            query.append(" and lower(d.phoneNumber) = :phoneNumber ");
            params.put("phoneNumber", dto.getPhoneNumber());
        }
        if (dto.getHeadOfDepartment() != null) {
            query.append(" and lower(d.headOfDepartment) like :headOfDepartment ");
            params.put("headOfDepartment", "%" + dto.getHeadOfDepartment().toLowerCase() + "%");
        }
        if (dto.getType() != null) {
            query.append(" and d.type = :type ");
            params.put("type", dto.getType());
        }

        if (dto.getCreatedDateTo() != null && dto.getCreatedDateFrom() != null) {
            query.append(" and d.createdDate between :createdDateFrom and :createdDateTo ");
            params.put("createdDateFrom", LocalDateTime.of(dto.getCreatedDateFrom(), LocalTime.MIN) );
            params.put("createdDateTo", LocalDateTime.of(dto.getCreatedDateTo(), LocalTime.MAX) );
        } else if (dto.getCreatedDateFrom() != null) {
            query.append(" and d.createdDate >= :createdDateFrom ");
            params.put("createdDateFrom", LocalDateTime.of(dto.getCreatedDateFrom(), LocalTime.MIN) );
        } else if (dto.getCreatedDateTo() != null) {
            query.append(" and d.createdDate <= :createdDateTo ");
            params.put("createdDateTo", LocalDateTime.of(dto.getCreatedDateTo(), LocalTime.MAX) );
        }

        if (dto.getUpdatedDateTo() != null && dto.getUpdatedDateFrom() != null) {
            query.append(" and d.updatedDate between :updatedDateFrom and :updatedDateTo ");
            params.put("updatedDateFrom", LocalDateTime.of(dto.getUpdatedDateFrom(), LocalTime.MIN));
            params.put("updatedDateTo", LocalDateTime.of(dto.getUpdatedDateTo(), LocalTime.MAX) );
        } else if (dto.getUpdatedDateFrom() != null) {
            query.append(" and d.updatedDate >= :updatedDateFrom ");
            params.put("updatedDateFrom", LocalDateTime.of(dto.getUpdatedDateFrom(), LocalTime.MIN));
        } else if (dto.getUpdatedDateTo() != null) {
            query.append(" and d.updatedDate <= :updatedDateTo ");
            params.put("updatedDateTo", LocalDateTime.of(dto.getUpdatedDateTo(), LocalTime.MAX) );
        }

        StringBuilder selectBuilder = new StringBuilder("select d from DepartmentEntity d ");
        selectBuilder.append(query);
        selectBuilder.append(" order by d.createdDate desc");

        StringBuilder countBuilder = new StringBuilder("select count(d) from DepartmentEntity d ");
        countBuilder.append(query);

        ///  Content
        Query selectQuery = entityManager.createQuery(selectBuilder.toString(), DepartmentEntity.class);
        params.forEach(selectQuery::setParameter);
        selectQuery.setFirstResult(page * size);
        selectQuery.setMaxResults(size);  // limit size
        List<DepartmentEntity> content = selectQuery.getResultList();

        ///  Tootal Count
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        params.forEach(countQuery::setParameter);
        Long totalCount = (Long) countQuery.getSingleResult();

        return new PageImpl<>(content, PageRequest.of(page, size), totalCount);
    }
}
