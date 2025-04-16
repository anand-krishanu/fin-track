package com.anand.fin_track_BACKEND.controller;

import com.anand.fin_track_BACKEND.dto.JwtResponse;
import com.anand.fin_track_BACKEND.dto.LoginRequest;
import com.anand.fin_track_BACKEND.dto.UserRequest;
import com.anand.fin_track_BACKEND.entity.Family;
import com.anand.fin_track_BACKEND.entity.User;
import com.anand.fin_track_BACKEND.security.JwtService;
import com.anand.fin_track_BACKEND.service.FamilyService;
import com.anand.fin_track_BACKEND.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    FamilyService familyService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequest newUser) {
        try {
            User user = new User();
            user.setEmail(newUser.getEmail());
            user.setName(newUser.getName());
            user.setPassword(newUser.getPassword());

            // Get the Family entity from FamilyService
            Family family = familyService.getFamilyById(newUser.getFamilyId());

            boolean isFirstUser = userService.getUsersByFamily(family.getId()).isEmpty();

            user.setRole(isFirstUser ? User.Role.ADMIN : User.Role.USER);

            user.setFamily(family);

            userService.saveUser(user, newUser.getFamilyId());

            return ResponseEntity.ok("User Registered Successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtService.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
