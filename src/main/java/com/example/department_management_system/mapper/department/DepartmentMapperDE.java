package com.example.department_management_system.mapper.department;

import com.example.department_management_system.dto.department.DepartmentDTO;
import com.example.department_management_system.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapperDE {
    /// To entity
    public DepartmentEntity toEntity(DepartmentDTO dto) {
        if (dto == null) {return null;}
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());

        entity.setDescription(dto.getDescription());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setHeadOfDepartment(dto.getHeadOfDepartment());
        entity.setType(dto.getType());
        entity.setStatus(dto.getStatus());

        entity.setVisible(dto.getVisible());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());
        return entity;
    }
    /// To DTO
    public DepartmentDTO toDto(DepartmentEntity entity) {
        if (entity == null) {return null;}
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setHeadOfDepartment(entity.getHeadOfDepartment());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());

        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }
}
