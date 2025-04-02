package com.anand.fin_track_BACKEND.controller;

import com.anand.fin_track_BACKEND.entity.Expense;
import com.anand.fin_track_BACKEND.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/{id}")
    public Optional<Expense> getExpanseById (@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping
    public List<Expense> getAllExpenses () {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public ResponseEntity<?> addFamily (@RequestBody Expense expense) {
        expenseService.saveExpense(expense);

        return ResponseEntity.ok("Expense Added!");
    }

    @PutMapping
    public ResponseEntity<?> updateFamilyById (@PathVariable Long id, @RequestBody Expense expense) {
        expenseService.updateExpense(id, expense);

        return ResponseEntity.ok("Expanse Updated");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFamilyById (@PathVariable Long id) {
        expenseService.deleteExpense(id);

        return ResponseEntity.ok("Expense Deleted!");
    }
}
