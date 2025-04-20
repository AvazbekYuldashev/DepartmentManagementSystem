package com.example.department_management_system.controller.employee;


import com.example.department_management_system.dto.AppResponse;
import com.example.department_management_system.dto.RegistrationDTO;
import com.example.department_management_system.dto.auth.AuthResponseDTO;
import com.example.department_management_system.dto.employee.EmployeeDTO;
import com.example.department_management_system.dto.auth.AuthDTO;
import com.example.department_management_system.dto.auth.ResponseDTO;

import com.example.department_management_system.dto.employee.EmployeeRequestDTO;
import com.example.department_management_system.enums.AppLangulage;
import com.example.department_management_system.service.AuthService;
import com.example.department_management_system.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth APIs", description = "API list for managing Auth")

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private ProfileService profileService;

//    @PostMapping("/registration")
//    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO profileDTO) {
//        EmployeeDTO result = profileService.registration(profileDTO);
//        return ResponseEntity.ok().body(result);
//    }

//    @PostMapping("/authorization")
//    public ResponseEntity<ResponseDTO> authorization(@RequestBody AuthDTO authDTO) {
//        ResponseDTO result = profileService.authorization(authDTO);
//        return ResponseEntity.ok().body(result);
//    }




    @Operation(summary = "Create User", description = "Api used for creating new User")
    @PostMapping("/registration")
    public ResponseEntity<AppResponse<String>> registration(@Valid @RequestBody RegistrationDTO dto,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLangulage lang) {

        return ResponseEntity.ok().body(authService.registration(dto, lang));
    }

    @Operation(summary = "verification by link", description = "API used for verification")
    @GetMapping("/registration/verification/{token}/{lang}")
    public ResponseEntity<String> regVerification(@PathVariable("token") String token,
                                                  @PathVariable("lang") AppLangulage lang) {
        return ResponseEntity.ok().body(authService.regVerification(token, lang));
    }

    @Operation(summary = "login by username and password", description = "API used for Login")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody EmployeeRequestDTO dto,
                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLangulage lang) {
        return ResponseEntity.ok().body(authService.login(dto, lang));
    }
}
