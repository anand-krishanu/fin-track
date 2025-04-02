package com.anand.fin_track_BACKEND.controller;

import com.anand.fin_track_BACKEND.dto.UserRequest;
import com.anand.fin_track_BACKEND.entity.Family;
import com.anand.fin_track_BACKEND.entity.User;
import com.anand.fin_track_BACKEND.security.JwtService;
import com.anand.fin_track_BACKEND.service.FamilyService;
import com.anand.fin_track_BACKEND.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    FamilyService familyService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest newUser) {
        try {
            User user = new User();
            user.setEmail(newUser.getEmail());
            user.setName(newUser.getName());
            user.setPassword(newUser.getPassword());

            // Get the Family entity from FamilyService
            Family family = familyService.getFamilyById(newUser.getFamilyId())
                    .orElseThrow(() -> new RuntimeException("Family Not Found"));

            boolean isFirstUser = userService.getUsersByFamily(family.getId()).isEmpty();

            user.setRole(isFirstUser ? User.Role.ROLE_ADMIN : User.Role.ROLE_USER);

            user.setFamily(family);

            userService.saveUser(user, newUser.getFamilyId());

            return ResponseEntity.ok("User Registered Successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public String login (@RequestBody  User user) {
        return jwtService.generateToken(user.getEmail());
    }
}
