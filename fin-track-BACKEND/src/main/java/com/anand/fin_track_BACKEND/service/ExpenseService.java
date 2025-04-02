package com.anand.fin_track_BACKEND.service;

import com.anand.fin_track_BACKEND.entity.Expense;
import com.anand.fin_track_BACKEND.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    public Expense saveExpense (Expense expense) {
        return expenseRepository.save(expense);
    }

    public Optional<Expense> getExpenseById (Long id) {
        return Optional.of(expenseRepository.findById(id))
                .orElseThrow(() -> new RuntimeException("Expense Not Found!"));
    }

    public List<Expense> getAllExpenses () {
        return expenseRepository.findAll();
    }

    public Expense updateExpense (Long id, Expense newExpense) {
        Optional<Expense> expense1 = expenseRepository.findById(id);

        if(expense1.isPresent()) {
            Expense existingExpense = new Expense();
            existingExpense.setAmount(newExpense.getAmount());
            existingExpense.setDate(newExpense.getDate());
            existingExpense.setCategory(newExpense.getCategory());
            existingExpense.setDescription(newExpense.getDescription());

            return expenseRepository.save(existingExpense);
        }
        else {
            throw new RuntimeException("Expense Not Found!");
        }
    }

    public void deleteExpense (Long id) {
        expenseRepository.deleteById(id);
    }

}
