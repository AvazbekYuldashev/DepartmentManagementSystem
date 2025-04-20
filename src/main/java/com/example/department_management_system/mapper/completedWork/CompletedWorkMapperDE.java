package com.example.department_management_system.mapper.completedWork;

import com.example.department_management_system.dto.completedWork.CompletedWorkDTO;
import com.example.department_management_system.entity.CompletedWorkEntity;
import org.springframework.stereotype.Component;

@Component
public class CompletedWorkMapperDE {
    ///  ToDTO --> (Entity'ni DTO'ga o‘tkazish).
    public CompletedWorkDTO toDTO(CompletedWorkEntity entity) {
        if (entity == null) {return null;}
        CompletedWorkDTO dto = new CompletedWorkDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());

        dto.setDepartmentId(entity.getDepartmentId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setApplicationId(entity.getApplicationId());
        return dto;
    }

    ///  ToEntity --> (DTO'ni Entity'ga o‘tkazish).
    public CompletedWorkEntity toEntity(CompletedWorkDTO dto) {
        if (dto == null) {return null;}
        CompletedWorkEntity entity = new CompletedWorkEntity();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());
        entity.setVisible(dto.getVisible());

        entity.setDepartmentId(dto.getDepartmentId());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setApplicationId(dto.getApplicationId());
        return entity;
    }
}
