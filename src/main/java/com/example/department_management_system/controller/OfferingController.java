package com.example.department_management_system.controller;


import com.example.department_management_system.dto.offering.OfferingDTO;
import com.example.department_management_system.dto.offering.OfferingFilterDTO;
import com.example.department_management_system.enums.AppLangulage;
import com.example.department_management_system.mapper.offering.OfferingMapper;
import com.example.department_management_system.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// Xizmatlar
@RestController
@RequestMapping("/offering")
public class OfferingController {
    @Autowired
    private OfferingService offeringService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PostMapping("")
    public ResponseEntity<OfferingDTO> createOffering(@RequestBody OfferingDTO offeringDTO) {
        OfferingDTO createdOffering = offeringService.create(offeringDTO);
        return ResponseEntity.ok(createdOffering);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<OfferingMapper>> getAllOfferings() {
        List<OfferingMapper> getAll = offeringService.getAll();
        return new ResponseEntity<>(getAll, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OfferingMapper> getOfferingById(@PathVariable("id") Integer id) {
        OfferingMapper byId = offeringService.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/{id}/department")
    public ResponseEntity<List<OfferingMapper>> getAllByDepartment(@PathVariable("id") Integer id) {
        List<OfferingMapper> byDepartment = offeringService.getByDepartmentId(id);
        return ResponseEntity.ok(byDepartment);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PutMapping("/{id}/department")
    public ResponseEntity<?> updateDepartmentIdP(@PathVariable("id") Integer id, @RequestBody OfferingDTO offeringDTO, AppLangulage lang) { // todo lang
        Boolean offering = offeringService.updateDepartmentIdP(id, offeringDTO, lang);
        return new ResponseEntity<>(offering, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOffering(@PathVariable("id") Integer id, @RequestBody OfferingDTO offeringDTO) {
        Boolean offering = offeringService.updateOffering(id, offeringDTO);
        return new ResponseEntity<>(offering, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PatchMapping("/{id}/wipe")
    public ResponseEntity<?> deleteWipe(@PathVariable("id") Integer id, @RequestBody OfferingFilterDTO offeringDTO) {
        Boolean isUpdate = offeringService.deleteWipe(id, offeringDTO.getVisible());
        return new ResponseEntity<>(isUpdate, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> updateVisible(@PathVariable("id") Integer id) {
        Boolean isUpdate = offeringService.deleteById(id);
        return new ResponseEntity<>(isUpdate, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/paged")
    public ResponseEntity<PageImpl<OfferingMapper>> pagination(@RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
        PageImpl<OfferingMapper> offeringDTOS = offeringService.pagination(getCurrentPage(page), size);
        return new ResponseEntity<>(offeringDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @PostMapping("/filter")
    public ResponseEntity<PageImpl<OfferingDTO>> filter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                        @RequestParam(value = "size", defaultValue = "30") int size,
                                                        @RequestBody OfferingFilterDTO offeringFilterDTO) {
        PageImpl<OfferingDTO> departmentDTO = offeringService.filter(offeringFilterDTO, getCurrentPage(page), size);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    @GetMapping("/pageds")
    public ResponseEntity<PageImpl<OfferingMapper>> getByDepartmentId(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("departmentId") Integer departmentId) {

        PageImpl<OfferingMapper> offeringDTOS = offeringService.paginationByDepartmentId(getCurrentPage(page), size, departmentId);
        return new ResponseEntity<>(offeringDTOS, HttpStatus.OK);
    }


    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}