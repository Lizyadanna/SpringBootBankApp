package com.myspringbootapp.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
public class Account {

    public enum AccountType {
        SAVINGS,
        CHECKING,
        FIXED_DEPOSIT,
        CURRENT
    }

    public enum AccountStatus {
        ACTIVE,
        INACTIVE,
        DORMANT,
        CLOSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="account_number", nullable = false, unique = true)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="account_type", nullable = false)
    private AccountType accountType;

    @Column(name="balance", nullable = false)
    private BigDecimal balance;

    @Column(name="open_date", nullable = false)
    private LocalDateTime openDate;

    @Enumerated(EnumType.STRING)
    @Column(name="account_status", nullable = false)
    private AccountStatus accountStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    // Custom constructors

    // This constructor ensures every account starts in a valid, consistent state.
    public Account(){
        this.openDate = LocalDateTime.now();
        this.accountStatus = AccountStatus.ACTIVE;
        this.balance = BigDecimal.ZERO;
    }

    public Account(String accountNumber, AccountType accountType, Customer customer, Branch branch) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customer = customer;
        this.branch = branch;
        this.openDate = LocalDateTime.now();
        this.accountStatus = AccountStatus.ACTIVE;
        this.balance = BigDecimal.ZERO;
    }
    // Overridden toString() method because of lazy loading of branch and customer entities
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", customerId=" + (customer != null ? customer.getId() : "null") +
                '}';
    }

}
