package com.anand.fin_track_BACKEND.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recurring_expense")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecurringExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Mapped to User Table
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double amount;
    private String category;
    private String description;

    // Weekly. Monthly, Yearly
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    public enum Frequency {
        WEEKLY, MONTHLY, YEARLY
    }
}
