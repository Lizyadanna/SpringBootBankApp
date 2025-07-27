package com.myspringbootapp.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

    @Data
    @Entity
    public class Transaction {
        public enum TransactionType {
            DEPOSIT, WITHDRAWAL, TRANSFER, PAYMENT
        }

        public enum TransactionStatus {
            PENDING, COMPLETED, FAILED
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        @Column(name = "transaction_type", nullable = false)
        private TransactionType type;

        @Enumerated(EnumType.STRING)
        @Column(name = "transaction_status", nullable = false)
        private TransactionStatus status;

        @NotNull
        @Positive // Defensive programming ensuring amount is > 0
        @Column(name = "transaction_amount", nullable = false)
        private BigDecimal amount;

        @Column(name = "transaction_date", nullable = false)
        private LocalDateTime transactionDate;

        @Column(name = "reference", nullable = false, length = 255)
        private String reference;

        // Many transactions to one account
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id", nullable = false)
        private Account account;

        // Constructors
        public Transaction() {
            this.transactionDate = LocalDateTime.now();
            this.status = TransactionStatus.PENDING;
        }

        public Transaction(TransactionType type, BigDecimal amount, String reference, Account account) {
            this.type = type;
            this.amount = amount;
            this.reference = reference;
            this.account = account;
            this.transactionDate = LocalDateTime.now();
            this.status = TransactionStatus.PENDING;
        }

        // Overridden toString() method because of lazy loading of account entity
        @Override
        public String toString() {
            return "Transaction{" +
                    "id=" + id +
                    ", type=" + type +
                    ", amount=" + amount +
                    ", status=" + status +
                    ", accountId=" + (account != null ? account.getId() : "null") +
                    '}';
        }
}
