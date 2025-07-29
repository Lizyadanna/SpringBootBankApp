package com.myspringbootapp.demo.service;

import com.myspringbootapp.demo.entity.Account;
import com.myspringbootapp.demo.entity.Transaction;
import com.myspringbootapp.demo.repository.AccountRepository;
import com.myspringbootapp.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        // Set account relationship if accountId provided
        if (transaction.getAccountId() != null && transaction.getAccount() == null) {
            Account account = accountRepository.findById(transaction.getAccountId())
                    .orElseThrow(() -> new RuntimeException("Account not found with ID: " + transaction.getAccountId()));
            transaction.setAccount(account);
        }

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}