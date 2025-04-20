package com.example.department_management_system.controller;


import com.example.department_management_system.dto.completedWork.CompletedWorkDTO;
import com.example.department_management_system.dto.completedWork.CompletedWorkFilterDTO;
import com.example.department_management_system.mapper.completedWork.CompletedWorkMapper;
import com.example.department_management_system.service.CompletedWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

///  Bajarilgan ishlar
@RestController
@RequestMapping("/completedWork")
public class CompletedWorkController {
    @Autowired
    private CompletedWorkService completedWorkService;
    ///  Get all
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/all")
    private ResponseEntity<List<CompletedWorkMapper>> getAll() {
        List<CompletedWorkMapper> completedWorkDTO = completedWorkService.getAll();
        return new ResponseEntity<>(completedWorkDTO, HttpStatus.OK);
    }
    ///  Get By Id
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CompletedWorkMapper> getById(@PathVariable("id") Integer id) {
        CompletedWorkMapper completedWorkDTO = completedWorkService.getById(id);
        if (completedWorkDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(completedWorkDTO, HttpStatus.OK);
    }
    /// Get By Application Id
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/{id}/application")
    public ResponseEntity<List<CompletedWorkMapper>> getByApplication(@PathVariable("id") Integer id) {
        List<CompletedWorkMapper> completedWorkDTO = completedWorkService.findByApplication(id);
        return new ResponseEntity<>(completedWorkDTO, HttpStatus.OK);
    }
    /// Get By applicaation Id
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/{id}/department")
    public ResponseEntity<List<CompletedWorkMapper>> getByDepartmentId(@PathVariable("id") Integer id) {
        List<CompletedWorkMapper> completedWorkDTO = completedWorkService.findByDepartmentId(id);
        return new ResponseEntity<>(completedWorkDTO, HttpStatus.OK);
    }
    /// Get By Complated Employee Id
    @GetMapping("/{id}/employee")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<List<CompletedWorkMapper>> getByEmployeeId(@PathVariable("id") Integer id) {
        List<CompletedWorkMapper> completedWorkDTO = completedWorkService.findByEmployeeId(id);
        return new ResponseEntity<>(completedWorkDTO, HttpStatus.OK);
    }
    ///  Delete by Id for visible
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PatchMapping("/{id}/wipe")
    public ResponseEntity<?> deleteWipe(@PathVariable("id") Integer id, @RequestBody CompletedWorkDTO completedWorkDTO) {
        Boolean isUpdate = completedWorkService.deleteWipe(id, completedWorkDTO.getVisible());
        return new ResponseEntity<>(isUpdate, HttpStatus.OK);
    }
    ///  Pagination
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/paged")
    public ResponseEntity<PageImpl<CompletedWorkMapper>> pagination(@RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
        PageImpl<CompletedWorkMapper> completedWorkDTOS = completedWorkService.pagination(getCurrentPage(page), size);
        return new ResponseEntity<>(completedWorkDTOS, HttpStatus.OK);
    }
    ///  Filter
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PostMapping("/filter")
    public ResponseEntity<PageImpl<CompletedWorkDTO>> filter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                          @RequestParam(value = "size", defaultValue = "30") int size,
                                                          @RequestBody CompletedWorkFilterDTO completedWorkFilterDTO) {
        PageImpl<CompletedWorkDTO> completedWorkDTO = completedWorkService.filter(completedWorkFilterDTO, getCurrentPage(page), size);
        return new ResponseEntity<>(completedWorkDTO, HttpStatus.OK);
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
