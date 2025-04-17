package com.example.department_management_system.repository.employee;

import com.example.department_management_system.dto.employee.EmployeeFilterDTO;
import com.example.department_management_system.entity.EmployeeEntity;
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
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageImpl<EmployeeEntity> filter(EmployeeFilterDTO dto, int page, int size) {
        StringBuilder query = new StringBuilder(" where e.visible = true ");
        Map<String, Object> params = new HashMap<>();

        if (dto.getId() != null) {
            query.append(" and e.id = :id ");
            params.put("id", dto.getId());
        }
        if (dto.getName() != null) {
            query.append(" and e.name like :name ");
            params.put("name", "%" + dto.getName() + "%");
        }
        if (dto.getSurname() != null) {
            query.append(" and e.surname like :surname ");
            params.put("surname", "%" + dto.getSurname() + "%");
        }
        if (dto.getRole() != null) {
            query.append(" and e.role = :role ");
            params.put("role", dto.getRole());
        }
        if (dto.getPosition() != null) {
            query.append(" and lower(e.position) like :position ");
            params.put("position", "%" + dto.getPosition().toLowerCase() + "%");
        }
        if (dto.getStatus() != null) {
            query.append(" and e.status = :status ");
            params.put("status", dto.getStatus());
        }
        if (dto.getCreatedDateTo() != null && dto.getCreatedDateFrom() != null) {
            query.append(" and e.createdDate between :createdDateFrom and :createdDateTo ");
            params.put("createdDateFrom", LocalDateTime.of(dto.getCreatedDateFrom(), LocalTime.MIN) );
            params.put("createdDateTo", LocalDateTime.of(dto.getCreatedDateTo(), LocalTime.MAX) );
        } else if (dto.getCreatedDateFrom() != null) {
            query.append(" and e.createdDate >= :createdDateFrom ");
            params.put("createdDateFrom", LocalDateTime.of(dto.getCreatedDateFrom(), LocalTime.MIN) );
        } else if (dto.getCreatedDateTo() != null) {
            query.append(" and e.createdDate <= :createdDateTo ");
            params.put("createdDateTo", LocalDateTime.of(dto.getCreatedDateTo(), LocalTime.MAX) );
        }
        if (dto.getUpdatedDateTo() != null && dto.getUpdatedDateFrom() != null) {
            query.append(" and e.updatedDate between :updatedDateFrom and :updatedDateTo ");
            params.put("updatedDateFrom", LocalDateTime.of(dto.getUpdatedDateFrom(), LocalTime.MIN));
            params.put("updatedDateTo", LocalDateTime.of(dto.getUpdatedDateTo(), LocalTime.MAX) );
        } else if (dto.getUpdatedDateFrom() != null) {
            query.append(" and e.updatedDate >= :updatedDateFrom ");
            params.put("updatedDateFrom", LocalDateTime.of(dto.getUpdatedDateFrom(), LocalTime.MIN));
        } else if (dto.getUpdatedDateTo() != null) {
            query.append(" and e.updatedDate <= :updatedDateTo ");
            params.put("updatedDateTo", LocalDateTime.of(dto.getUpdatedDateTo(), LocalTime.MAX) );
        }
        if (dto.getDepartmentId() != null) {
            query.append(" and e.department.id = :departmentId ");
            params.put("departmentId", dto.getDepartmentId());
        }
        if (dto.getEmail() != null) {
            query.append(" and e.login = :login ");
            params.put("login", dto.getEmail());
        }
        if (dto.getPassword() != null) {
            query.append(" and e.password = :password ");
            params.put("password", dto.getPassword());
        }

        StringBuilder selectBuilder = new StringBuilder("select e from EmployeeEntity e ");
        selectBuilder.append(query);
        selectBuilder.append(" order by e.createdDate desc");

        StringBuilder countBuilder = new StringBuilder("select count(e) from EmployeeEntity e ");
        countBuilder.append(query);

        ///  Content
        Query selectQuery = entityManager.createQuery(selectBuilder.toString(), EmployeeEntity.class);
        params.forEach(selectQuery::setParameter);
        selectQuery.setFirstResult(page * size);
        selectQuery.setMaxResults(size);  // limit size
        List<EmployeeEntity> content = selectQuery.getResultList();

        ///  Tootal Count
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        params.forEach(countQuery::setParameter);
        Long totalCount = (Long) countQuery.getSingleResult();

        return new PageImpl<>(content, PageRequest.of(page, size), totalCount);

    }
}
