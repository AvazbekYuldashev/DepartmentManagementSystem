package com.example.department_management_system.controller;

import com.example.department_management_system.dto.department.DepartmentCreateDTO;
import com.example.department_management_system.dto.department.DepartmentDTO;
import com.example.department_management_system.dto.department.DepartmentFilterDTO;
import com.example.department_management_system.dto.department.DepartmentUpdateDTO;
import com.example.department_management_system.mapper.department.DepartmentMapper;
import com.example.department_management_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

///  Bolim
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PostMapping("")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentCreateDTO dto) {
        return new ResponseEntity<>(departmentService.createDepartment(dto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllDepartment() {
        List<DepartmentMapper> all = departmentService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") Integer id) {
        DepartmentMapper byId = departmentService.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Integer id, @RequestBody DepartmentUpdateDTO dto) {
        Boolean isUpdated = departmentService.update(id, dto);
        return new ResponseEntity<>(isUpdated, isUpdated ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Integer id, @RequestBody DepartmentUpdateDTO dto) {
        Boolean isUpdated = departmentService.updateStatus(id, dto);
        return new ResponseEntity<>(isUpdated, isUpdated ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PatchMapping("/{id}/wipe")
    public ResponseEntity<?> deleteWipe(@PathVariable("id") Integer id, @RequestBody DepartmentFilterDTO departmentDTO) {
        Boolean isUpdate = departmentService.deleteWipe(id, departmentDTO.getVisible());
        return new ResponseEntity<>(isUpdate, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Boolean isUpdate = departmentService.deleteById(id);
        return new ResponseEntity<>(isUpdate, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/paged")
    public ResponseEntity<PageImpl<DepartmentMapper>> pagination(@RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        PageImpl<DepartmentMapper> departmentDTO = departmentService.pagination(getCurrentPage(page), size);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PostMapping("/filter")
    public ResponseEntity<PageImpl<DepartmentDTO>> filter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                          @RequestParam(value = "size", defaultValue = "30") int size,
                                                          @RequestBody DepartmentFilterDTO departmentFilterDTO) {
        PageImpl<DepartmentDTO> departmentDTO = departmentService.filter(departmentFilterDTO, getCurrentPage(page), size);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}