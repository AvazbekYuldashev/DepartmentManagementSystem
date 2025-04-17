package com.example.department_management_system.repository.offering;

import com.example.department_management_system.dto.offering.OfferingFilterDTO;
import com.example.department_management_system.entity.OfferingEntity;
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
public class OfferingCustomRepositoryImpl implements OfferingCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageImpl<OfferingEntity> filter(OfferingFilterDTO offeringDTO, int page, int size) {
        StringBuilder query = new StringBuilder(" where o.visible = true ");
        Map<String, Object> params = new HashMap<>();

        if (offeringDTO.getId() != null) {
            query.append(" and o.id = :id ");
            params.put("id", offeringDTO.getId());
        }
        if (offeringDTO.getTitle() != null) {
            query.append(" and lower(o.title) like :title ");
            params.put("title", "%" + offeringDTO.getTitle().toLowerCase() + "%");
        }
        if (offeringDTO.getDescription() != null) {
            query.append(" and lower(o.description) like :description ");
            params.put("description", "%" + offeringDTO.getDescription().toLowerCase() + "%");
        }
        if (offeringDTO.getCreatedDateTo() != null && offeringDTO.getCreatedDateFrom() != null) {
            query.append(" and o.createdDate between :createdDateFrom and :createdDateTo ");
            params.put("createdDateFrom", LocalDateTime.of(offeringDTO.getCreatedDateFrom(), LocalTime.MIN) );
            params.put("createdDateTo", LocalDateTime.of(offeringDTO.getCreatedDateTo(), LocalTime.MAX) );
        } else if (offeringDTO.getCreatedDateFrom() != null) {
            query.append(" and o.createdDate >= :createdDateFrom ");
            params.put("createdDateFrom", LocalDateTime.of(offeringDTO.getCreatedDateFrom(), LocalTime.MIN) );
        } else if (offeringDTO.getCreatedDateTo() != null) {
            query.append(" and o.createdDate <= :createdDateTo ");
            params.put("createdDateTo", LocalDateTime.of(offeringDTO.getCreatedDateTo(), LocalTime.MAX) );
        }
        if (offeringDTO.getUpdatedDateTo() != null && offeringDTO.getUpdatedDateFrom() != null) {
            query.append(" and o.updatedDate between :updatedDateFrom and :updatedDateTo ");
            params.put("updatedDateFrom", LocalDateTime.of(offeringDTO.getUpdatedDateFrom(), LocalTime.MIN));
            params.put("updatedDateTo", LocalDateTime.of(offeringDTO.getUpdatedDateTo(), LocalTime.MAX) );
        } else if (offeringDTO.getUpdatedDateFrom() != null) {
            query.append(" and o.updatedDate >= :updatedDateFrom ");
            params.put("updatedDateFrom", LocalDateTime.of(offeringDTO.getUpdatedDateFrom(), LocalTime.MIN));
        } else if (offeringDTO.getUpdatedDateTo() != null) {
            query.append(" and o.updatedDate <= :updatedDateTo ");
            params.put("updatedDateTo", LocalDateTime.of(offeringDTO.getUpdatedDateTo(), LocalTime.MAX) );
        }
        if (offeringDTO.getDepartmentId() != null) {
            query.append(" and o.department.id = :departmentId ");
            params.put("departmentId", offeringDTO.getDepartmentId());
        }

        StringBuilder selectBuilder = new StringBuilder("select o from OfferingEntity o ");
        selectBuilder.append(query);
        selectBuilder.append(" order by o.createdDate desc");

        StringBuilder countBuilder = new StringBuilder("select count(o) from OfferingEntity o ");
        countBuilder.append(query);

        ///  Content
        Query selectQuery = entityManager.createQuery(selectBuilder.toString(), OfferingEntity.class);
        params.forEach(selectQuery::setParameter);
        selectQuery.setFirstResult(page * size);
        selectQuery.setMaxResults(size);  // limit size
        List<OfferingEntity> content = selectQuery.getResultList();

        ///  Tootal Count
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        params.forEach(countQuery::setParameter);
        Long totalCount = (Long) countQuery.getSingleResult();

        return new PageImpl<>(content, PageRequest.of(page, size), totalCount);

    }
}
