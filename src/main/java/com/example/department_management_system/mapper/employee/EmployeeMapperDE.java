package com.example.department_management_system.mapper.employee;

import com.example.department_management_system.dto.employee.EmployeeDTO;
import com.example.department_management_system.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperDE {
    /// Method to convert EmployeeEntity to EmployeeDTO
    public EmployeeDTO toDto(EmployeeEntity entity) {
        if (entity == null) {return null;}
        EmployeeDTO dto = new EmployeeDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setPosition(entity.getPosition());

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setDepartmentId(entity.getDepartment().getId());

        dto.setVisible(entity.getVisible());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());


        return dto;
    }
    /// Method to convert EmployeeDTO to EmployeeEntity
    public EmployeeEntity toEntity(EmployeeDTO dto) {
        if (dto == null) {return null;}
        EmployeeEntity entity = new EmployeeEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPosition(dto.getPosition());

        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());
        entity.setDepartmentId(dto.getDepartmentId());

        entity.setVisible(dto.getVisible());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        return entity;
    }
}
