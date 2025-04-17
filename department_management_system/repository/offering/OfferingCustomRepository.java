package com.example.department_management_system.repository.offering;

import com.example.department_management_system.dto.offering.OfferingFilterDTO;
import com.example.department_management_system.entity.OfferingEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferingCustomRepository {
    PageImpl<OfferingEntity> filter(OfferingFilterDTO offeringDTO, int page, int size);
}
