package com.example.department_management_system.repository.department;

import com.example.department_management_system.dto.department.DepartmentFilterDTO;
import com.example.department_management_system.entity.DepartmentEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentCustomRepository {
    PageImpl<DepartmentEntity> filter(DepartmentFilterDTO departmentDTO, int page, int size);
}
