package com.myspringbootapp.demo.controller;

import com.myspringbootapp.demo.entity.Transaction;
import com.myspringbootapp.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Transaction entities in the wealth management system
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService; // Dependency injection

    /**
     * Create a new transaction
     * POST /api/transactions
     */
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    /**
     * Get all transactions
     * GET /api/transactions
     */
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    /**
     * Get transaction by ID
     * GET /api/transactions/{id}
     */
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    /**
     * Update an existing transaction
     * PUT /api/transactions/{id}
     */
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        // Ensure the transaction has the correct ID
        transaction.setId(id);
        return transactionService.saveTransaction(transaction);
    }

    /**
     * Delete a transaction
     * DELETE /api/transactions/{id}
     */
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    /**
     * Get transactions for a specific account
     * GET /api/transactions/account/{accountId}
     */
    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsByAccount(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}
