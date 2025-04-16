package com.anand.fin_track_BACKEND.service;

import com.anand.fin_track_BACKEND.entity.RecurringExpense;
import com.anand.fin_track_BACKEND.entity.User;
import com.anand.fin_track_BACKEND.repository.RecurringExpenseRepository;
import com.anand.fin_track_BACKEND.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecurringExpenseService {

    @Autowired
    private RecurringExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public RecurringExpense addExpense(RecurringExpense expense, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<RecurringExpense> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public RecurringExpense updateExpense(Long id, RecurringExpense updated) {
        RecurringExpense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setAmount(updated.getAmount());
        expense.setCategory(updated.getCategory());
        expense.setDescription(updated.getDescription());
        expense.setFrequency(updated.getFrequency());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<RecurringExpense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
