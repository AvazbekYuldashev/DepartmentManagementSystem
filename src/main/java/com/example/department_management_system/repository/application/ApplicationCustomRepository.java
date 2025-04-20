package com.example.department_management_system.repository.application;

import com.example.department_management_system.dto.application.ApplicationFilterDTO;
import com.example.department_management_system.entity.ApplicationEntity;
import org.springframework.data.domain.PageImpl;

public interface ApplicationCustomRepository {
    PageImpl<ApplicationEntity> filter(ApplicationFilterDTO applicationFilterDTO, int page, int size);
}
