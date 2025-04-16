package com.anand.fin_track_BACKEND.service;

import com.anand.fin_track_BACKEND.entity.Family;
import com.anand.fin_track_BACKEND.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {
    @Autowired
    private FamilyRepository familyRepository;

    public void saveFamily (Family family) {
        familyRepository.save(family);
    }

    public Family getFamilyById(Long id) {
        return familyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Family Not Found!"));
    }

    public List<Family> getAllFamilies () {
        return familyRepository.findAll();
    }

    public void updateFamily (Long id, Family family) {
        Optional<Family> family1 = familyRepository.findById(id);

        if(family1.isPresent()) {
            Family existingFamily = family1.get();

            existingFamily.setName(family.getName());

            familyRepository.save(existingFamily);
        }
        else {
            throw new RuntimeException("Family Not Found!");
        }
    }

    public void deleteFamilyById (Long id) {
        familyRepository.deleteById(id);
    }
}
