package com.myspringbootapp.demo.service;

import com.myspringbootapp.demo.entity.Transaction;
import com.myspringbootapp.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

        // Constructor injection is used here to inject the TransactionRepository dependency
        private final TransactionRepository transactionRepository;

        @Autowired
        public TransactionService(TransactionRepository transactionRepository) {
            this.transactionRepository = transactionRepository;
        }

        public List<Transaction> getAllTransactions() {
            return transactionRepository.findAll();
        }

        public Transaction getTransactionById(Long id) {
            return transactionRepository.findById(id).orElse(null);
        }

        public Transaction saveTransaction(Transaction transaction) {
            return transactionRepository.save(transaction);
        }

        public void deleteTransaction(Long id) {
            transactionRepository.deleteById(id);
        }

        public List<Transaction> getTransactionsByAccountId(Long accountId) {
            return transactionRepository.findByAccountId(accountId);
        }
}
