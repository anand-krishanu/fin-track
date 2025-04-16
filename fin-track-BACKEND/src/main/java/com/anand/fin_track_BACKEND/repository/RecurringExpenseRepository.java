package com.anand.fin_track_BACKEND.repository;

import com.anand.fin_track_BACKEND.entity.RecurringExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense, Long> {
    List<RecurringExpense> findByUserId(Long userId);
}
