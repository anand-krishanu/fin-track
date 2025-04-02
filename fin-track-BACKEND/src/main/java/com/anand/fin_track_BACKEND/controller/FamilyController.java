package com.anand.fin_track_BACKEND.controller;

import com.anand.fin_track_BACKEND.entity.Family;
import com.anand.fin_track_BACKEND.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/family")
public class FamilyController {

    @Autowired
    FamilyService familyService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public Optional<Family> getFamilyById (@PathVariable Long id) {
        return familyService.getFamilyById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Family> getAllFamilies () {
        return familyService.getAllFamilies();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #id == authentication.principal.family.id")
    @PostMapping
    public ResponseEntity<?> addFamily (@RequestBody Family family) {
        familyService.saveFamily(family);

        return ResponseEntity.ok("Family Added!");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<?> updateFamilyById (@PathVariable Long id, @RequestBody Family family) {
        familyService.updateFamily(id, family);

        return ResponseEntity.ok("Family Updated");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFamilyById (@PathVariable Long id) {
        familyService.deleteFamilyById(id);

        return ResponseEntity.ok("Family Deleted!");
    }
}
