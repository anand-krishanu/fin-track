package com.anand.fin_track_BACKEND.controller;

import com.anand.fin_track_BACKEND.entity.RecurringExpense;
import com.anand.fin_track_BACKEND.service.RecurringExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring")
public class RecurringExpenseController {

    @Autowired
    private RecurringExpenseService expenseService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<RecurringExpense> addExpense(@PathVariable Long userId, @RequestBody RecurringExpense expense) {
        return ResponseEntity.ok(expenseService.addExpense(expense, userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecurringExpense>> getExpensesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseService.getExpensesByUser(userId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RecurringExpense> updateExpense(@PathVariable Long id, @RequestBody RecurringExpense updated) {
        return ResponseEntity.ok(expenseService.updateExpense(id, updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecurringExpense>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }
}
