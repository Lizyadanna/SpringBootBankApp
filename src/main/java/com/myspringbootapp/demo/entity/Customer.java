package com.myspringbootapp.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
public class Customer {

    public enum CustomerType {
        INDIVIDUAL,
        BUSINESS,
        CORPORATE
    }

    public enum CustomerStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED,
        CLOSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;


    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "phone_number", nullable = false)
    @NotBlank
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "address", nullable = false)
    @NotBlank
    private String address;

    @Column(name = "city", nullable = false)
    @NotBlank
    private String city;

    @Column(name = "state", nullable = false)
    @NotBlank
    private String state;

    @Column(name = "zip_code", nullable = false)
    @NotBlank
    private String zipCode;

    @Column(name = "ssn", unique = true)
    private String ssn;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", nullable = false)
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_status", nullable = false)
    private CustomerStatus customerStatus;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    // One customer can have multiple accounts
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts;

    // The branch where customer was registered
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_branch_id", nullable = false)
    @JsonIgnore
    private Branch homeBranch;

    // Custom constructors
    public Customer() {
        this.registrationDate = LocalDateTime.now();
        this.customerStatus = CustomerStatus.ACTIVE;
        this.customerType = CustomerType.INDIVIDUAL;  // Default to individual
    }

    public Customer(String firstName, String lastName, String email, String phoneNumber,
                    LocalDate dateOfBirth, String address, String city, String state,
                    String zipCode, Branch homeBranch) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.homeBranch = homeBranch;
        this.registrationDate = LocalDateTime.now();
        this.customerStatus = CustomerStatus.ACTIVE;
        this.customerType = CustomerType.INDIVIDUAL;
    }

    // Utility methods to make the object more useful
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFullAddress() {
        return address + ", " + city + ", " + state + " " + zipCode;
    }

    public boolean isActive() {
        return customerStatus == CustomerStatus.ACTIVE;
    }

    public int getAge() {
        if (dateOfBirth == null) return 0;
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    // Safe toString() method to avoid lazy loading issues
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", customerType=" + customerType +
                ", customerStatus=" + customerStatus +
                ", homeBranchId=" + (homeBranch != null ? homeBranch.getId() : "null") +
                '}';
    }

}