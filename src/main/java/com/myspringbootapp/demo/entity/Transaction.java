package com.myspringbootapp.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status", nullable = false)
    private TransactionStatus transactionStatus;

    @Column(name = "transaction_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "reference", nullable = false, length = 255)
    private String reference;

    @Column(name = "description")
    private String description;  // ← Added this

    // Relationship (hidden from JSON)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    // For JSON responses
    @Column(name = "account_id", insertable = false, updatable = false)
    private Long accountId;  // ← Added this

    // Constructor
    public Transaction() {
        this.transactionDate = LocalDateTime.now();
        this.transactionStatus = TransactionStatus.COMPLETED;
        this.reference = "TXN" + System.currentTimeMillis();  // ← Auto-generate
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", transactionStatus=" + transactionStatus +
                ", accountId=" + (account != null ? account.getId() : "null") +
                '}';
    }
}