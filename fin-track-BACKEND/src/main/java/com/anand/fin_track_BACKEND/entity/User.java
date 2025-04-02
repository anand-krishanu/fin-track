package com.anand.fin_track_BACKEND.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @ManyToOne
    @JoinColumn(name = "family_id", nullable = false)
    private Family family;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }
}