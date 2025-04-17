package com.example.department_management_system.repository.compledetWork;

import com.example.department_management_system.dto.completedWork.CompletedWorkFilterDTO;
import com.example.department_management_system.entity.CompletedWorkEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedWorkCustomRepository {
    PageImpl<CompletedWorkEntity> filter(CompletedWorkFilterDTO completedWorkFilterDTO, int page, int size);
}
