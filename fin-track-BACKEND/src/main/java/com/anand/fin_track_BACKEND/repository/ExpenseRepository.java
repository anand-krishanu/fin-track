package com.anand.fin_track_BACKEND.repository;

import com.anand.fin_track_BACKEND.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
