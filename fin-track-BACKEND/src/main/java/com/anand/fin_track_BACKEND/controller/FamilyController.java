package com.anand.fin_track_BACKEND.controller;

import com.anand.fin_track_BACKEND.entity.Family;
import com.anand.fin_track_BACKEND.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/family")
public class FamilyController {

    @Autowired
    FamilyService familyService;

    @GetMapping("/{id}")
    public Optional<Family> getFamilyById (@PathVariable Long id) {
        return familyService.getFamilyById(id);
    }

    @GetMapping
    public List<Family> getAllFamilies () {
        return familyService.getAllFamilies();
    }

    @PostMapping
    public ResponseEntity<?> addFamily (@RequestBody Family family) {
        familyService.saveFamily(family);

        return ResponseEntity.ok("Family Added!");
    }

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
