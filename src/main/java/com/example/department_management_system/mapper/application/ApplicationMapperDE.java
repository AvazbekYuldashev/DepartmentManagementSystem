package com.example.department_management_system.mapper.application;

import com.example.department_management_system.dto.application.ApplicationDTO;
import com.example.department_management_system.entity.ApplicationEntity;
import org.springframework.stereotype.Component;


@Component
public class ApplicationMapperDE {
    ///  ToDTO --> (Entity'ni DTO'ga o‘tkazish).
    public ApplicationDTO toDTO(ApplicationEntity entity) {
        if (entity == null) {throw new RuntimeException();}
        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());

        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setStatus(entity.getStatus());
        dto.setVisible(entity.getVisible());

        dto.setCreatedById(entity.getCreatedById());
        dto.setAssignedToId(entity.getAssignedToId());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setOfferingId(entity.getOfferingId());
        dto.setCompletedWorkId(entity.getCompletedWorkId());

        return dto;
    }
    ///  ToEntity --> (DTO'ni Entity'ga o‘tkazish).
    public ApplicationEntity toEntity(ApplicationDTO dto) {
        if (dto == null) {throw new RuntimeException();}
        ApplicationEntity entity = new ApplicationEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());

        entity.setDescription(dto.getDescription());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());
        entity.setStatus(dto.getStatus());
        entity.setVisible(dto.getVisible());

        entity.setCreatedById(dto.getCreatedById());
        entity.setAssignedToId(dto.getAssignedToId());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setOfferingId(dto.getOfferingId());
        entity.setCompletedWorkId(dto.getCompletedWorkId());

        return entity;
    }
}
