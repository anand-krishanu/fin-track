package com.anand.fin_track_BACKEND.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="families")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Family {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;
}
