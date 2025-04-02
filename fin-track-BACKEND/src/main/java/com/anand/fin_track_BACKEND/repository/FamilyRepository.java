package com.anand.fin_track_BACKEND.repository;

import com.anand.fin_track_BACKEND.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

}
