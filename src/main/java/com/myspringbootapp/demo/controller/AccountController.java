package com.myspringbootapp.demo.controller;


import com.myspringbootapp.demo.entity.Account;
import com.myspringbootapp.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Rest Controller for managing account entities in the database
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService; // Dependency injection

    /**
     * Create a new account
     * POST /api/accounts
     */
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    /**
     * Get all accounts
     * GET /api/accounts
     */
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    /**
     * Get account by ID
     * GET /api/accounts/{id}
     */
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    /**
     * Update an existing account
     * PUT /api/accounts/{id}
     */
    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        // Ensure the account has the correct ID
        account.setId(id);
        return accountService.updateAccount(account);
    }

    /**
     * Delete an account
     * DELETE /api/accounts/{id}
     */
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
