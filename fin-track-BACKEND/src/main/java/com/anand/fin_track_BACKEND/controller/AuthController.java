package com.anand.fin_track_BACKEND.controller;

import com.anand.fin_track_BACKEND.dto.UserRequest;
import com.anand.fin_track_BACKEND.entity.User;
import com.anand.fin_track_BACKEND.security.JwtService;
import com.anand.fin_track_BACKEND.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserRequest newUser) {
        try {
            User user = new User();
            user.setEmail(newUser.getEmail());
            user.setName(newUser.getName());
            user.setPassword(newUser.getPassword());
            user.setRole(User.Role.valueOf(newUser.getRole()));

            User savedUser = userService.saveUser(user, newUser.getFamilyId());

            return ResponseEntity.ok("User Registered Successfully!");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login (@RequestBody  User user) {
        return jwtService.generateToken(user.getEmail());
    }
}
