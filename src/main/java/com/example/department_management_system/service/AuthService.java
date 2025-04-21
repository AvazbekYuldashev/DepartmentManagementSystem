package com.example.department_management_system.service;

import com.example.department_management_system.dto.AppResponse;
import com.example.department_management_system.dto.RegistrationDTO;
import com.example.department_management_system.dto.auth.AuthResponseDTO;
import com.example.department_management_system.dto.employee.EmployeeRequestDTO;
import com.example.department_management_system.entity.EmployeeEntity;
import com.example.department_management_system.enums.AppLangulage;
import com.example.department_management_system.enums.EmployeeRole;
import com.example.department_management_system.enums.GeneralStatus;
import com.example.department_management_system.exp.AppBadExeption;
import com.example.department_management_system.exp.NotFoundExeption;
import com.example.department_management_system.repository.employee.EmployeeRepository;
import com.example.department_management_system.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BCryptPasswordEncoder bc;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ResourceBoundleService boundleService;


    public AppResponse<String> registration(RegistrationDTO dto, AppLangulage lang) {
        Optional<EmployeeEntity> optional = employeeRepository.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileService.deleteById(optional.get().getId());
                // send sms/email TODO
            } else {
                throw new AppBadExeption(boundleService.getMessage("email.phone.exists", lang));
            }
        }
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(bc.encode(dto.getPassword()));

        entity.setStatus(GeneralStatus.IN_REGISTRATION);
        entity.setVisible(true);
        entity.setRole(EmployeeRole.SUPERADMIN);

        entity.setCreatedDate(LocalDateTime.now());
        entity.setUpdatedDate(LocalDateTime.now());
        employeeRepository.save(entity);
        emailSendingService.sendRegistrationEmail(dto.getEmail(), entity.getId(), lang);
        return new AppResponse<>(boundleService.getMessage("email.confirm.send", lang));
    }

    public String regVerification(String token, AppLangulage lang) {
        try {
            Integer id = JwtUtil.decodeRegVerToken(token);
            EmployeeEntity profile = profileService.getById(id, lang);
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                // ACTIVE
                employeeRepository.changeStatus(profile.getId(), GeneralStatus.ACTIVE);
                return boundleService.getMessage("successfully.registered", lang);

            }
        } catch (JwtException e) {

        }
        throw new AppBadExeption(boundleService.getMessage("verification.failed", lang));
    }

    public AuthResponseDTO login(EmployeeRequestDTO dto, AppLangulage lang) {
        Optional<EmployeeEntity> optional = profileService.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isEmpty()) {
            throw new NotFoundExeption(boundleService.getMessage("username.not.found", lang));
        }
        EmployeeEntity profile = optional.get();
        if (!bc.matches(dto.getPassword(), profile.getPassword())) {
            throw new NotFoundExeption(boundleService.getMessage("username.not.found", lang));
        }
        if (!profile.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadExeption(boundleService.getMessage("wrong.status", lang));
        }
        AuthResponseDTO response = new AuthResponseDTO();
        response.setName(profile.getName());
        response.setEmail(profile.getEmail());
        response.setJwt(JwtUtil.encode(profile)); // retnrn jwt

        return response;
    }
}
