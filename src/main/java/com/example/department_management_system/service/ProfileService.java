package com.example.department_management_system.service;


import com.example.department_management_system.entity.EmployeeEntity;
import com.example.department_management_system.enums.AppLangulage;
import com.example.department_management_system.exp.AppBadRequestExeption;
import com.example.department_management_system.repository.employee.EmployeeRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity getById(Integer id, AppLangulage lang) {
        Optional<EmployeeEntity> optional = employeeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestExeption("Employee not found");
        }
        return optional.get();
    }

    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    public Optional<EmployeeEntity> findByEmailAndVisibleTrue(@NotBlank(message = "Email required") String email) {
        return employeeRepository.findByEmailAndVisibleTrue(email);
    }
}
