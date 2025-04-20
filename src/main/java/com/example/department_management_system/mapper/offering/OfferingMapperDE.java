package com.example.department_management_system.mapper.offering;

import com.example.department_management_system.dto.offering.OfferingDTO;
import com.example.department_management_system.entity.OfferingEntity;
import org.springframework.stereotype.Component;

@Component
public class OfferingMapperDE {
    public OfferingEntity toEntity(OfferingDTO dto) {
        if (dto == null) {return null;}
        OfferingEntity entity = new OfferingEntity();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());
        entity.setVisible(dto.getVisible());
        entity.setDepartmentId(dto.getDepartmentId());
        return entity;
    }

    // Convert OfferingEntity to OfferingDTO
    public OfferingDTO toDTO(OfferingEntity entity) {
        if (entity == null) {return null;}
        OfferingDTO dto = new OfferingDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());
        dto.setDepartmentId(entity.getDepartmentId());
        return dto;
    }
}
