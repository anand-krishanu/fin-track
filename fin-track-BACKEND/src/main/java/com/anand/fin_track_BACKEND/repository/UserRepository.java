package com.anand.fin_track_BACKEND.repository;

import com.anand.fin_track_BACKEND.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByFamilyId(long familyId);
}
