package com.anand.fin_track_BACKEND.service;

import com.anand.fin_track_BACKEND.dto.UserRequest;
import com.anand.fin_track_BACKEND.entity.Family;
import com.anand.fin_track_BACKEND.entity.User;
import com.anand.fin_track_BACKEND.repository.FamilyRepository;
import com.anand.fin_track_BACKEND.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Save User with Encoding the password
    public User saveUser(User user, Long familyId) {

        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new RuntimeException("Family not found"));

        user.setFamily(family);
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUser () {
        return userRepository.findAll();
    }

    public User getUserById (Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));

    }

    public User updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setRole(User.Role.valueOf(userRequest.getRole()));

        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            user.setPassword(encoder.encode(userRequest.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser (Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersByFamily (long familyId) {
        return userRepository.findByFamilyId(familyId);
    }
}
