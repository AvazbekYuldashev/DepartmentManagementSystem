package com.example.department_management_system.controller;


import com.example.department_management_system.dto.employee.EmployeeDTO;
import com.example.department_management_system.dto.auth.AuthDTO;
import com.example.department_management_system.dto.auth.ResponseDTO;
import com.example.department_management_system.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/registration")
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO profileDTO) {
        EmployeeDTO result = profileService.registration(profileDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/authorization")
    public ResponseEntity<ResponseDTO> authorization(@RequestBody AuthDTO authDTO) {
        ResponseDTO result = profileService.authorization(authDTO);
        return ResponseEntity.ok().body(result);
    }
}
