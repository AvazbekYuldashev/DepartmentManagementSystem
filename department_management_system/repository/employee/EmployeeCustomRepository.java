package com.example.department_management_system.repository.employee;


import com.example.department_management_system.dto.employee.EmployeeFilterDTO;
import com.example.department_management_system.entity.EmployeeEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeCustomRepository {
    PageImpl<EmployeeEntity> filter(EmployeeFilterDTO employeeDTO, int page, int size);


}
