package com.myspringbootapp.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;
import java.util.List;


@Data
@Entity
public class Branch {

    public void setName(String testBranch) {
    }

    public enum BranchType {
        HEADQUARTERS,           // Main office with executives
        PRIVATE_WEALTH_OFFICE,  // High-net-worth client services
        INVESTMENT_ADVISORY,    // Investment planning and advice
        FAMILY_OFFICE,          // Ultra-high-net-worth families
        REGIONAL_OFFICE         // Regional management hub
    }

    public enum BranchStatus {
        ACTIVE,
        INACTIVE,
        UNDER_RENOVATION,
        TEMPORARILY_CLOSED,
        PERMANENTLY_CLOSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "branch_name", nullable = false)
    @NotBlank
    private String branchName;

    @Column(name = "branch_code", nullable = false, unique = true)
    @NotBlank
    private String branchCode;  // Unique identifier like "HQ001", "PWO002"

    @Enumerated(EnumType.STRING)
    @Column(name = "branch_type", nullable = false)
    private BranchType branchType;

    @Enumerated(EnumType.STRING)
    @Column(name = "branch_status", nullable = false)
    private BranchStatus branchStatus;

    // Address Information
    @Column(name = "street_address", nullable = false)
    @NotBlank
    private String streetAddress;

    @Column(name = "city", nullable = false)
    @NotBlank
    private String city;

    @Column(name = "state", nullable = false)
    @NotBlank
    private String state;

    @Column(name = "zip_code", nullable = false)
    @NotBlank
    private String zipCode;

    @Column(name = "country", nullable = false)
    @NotBlank
    private String country;

    // Contact Information
    @Column(name = "phone_number", nullable = false)
    @NotBlank
    private String phoneNumber;

    @Column(name = "email")
    @Email
    private String email;

    // Operating Hours
    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @Column(name = "appointment_only")
    private Boolean appointmentOnly;

    // Branch Management
    @Column(name = "managing_director")
    private String managingDirector;

    @Column(name = "director_email")
    @Email
    private String directorEmail;

    @Column(name = "director_phone")
    private String directorPhone;

    // Relationships
    // One branch can have multiple employees
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

    // One branch can have multiple accounts
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts;

    // One branch can be home branch for multiple customers
    @OneToMany(mappedBy = "homeBranch", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Customer> homeCustomers;

    // Custom constructors

    // Default constructor with smart wealth management defaults
    public Branch() {
        this.branchStatus = BranchStatus.ACTIVE;
        this.branchType = BranchType.REGIONAL_OFFICE;  // Safe default
        this.appointmentOnly = false;
        this.country = "United States";
        this.openingTime = LocalTime.of(9, 0);   // 9:00 AM
        this.closingTime = LocalTime.of(17, 0);  // 5:00 PM
    }

    // Essential fields constructor (most commonly used)
    public Branch(String branchName, String branchCode, BranchType branchType,
                  String streetAddress, String city, String state, String zipCode, String phoneNumber) {
        this.branchName = branchName;
        this.branchCode = branchCode;
        this.branchType = branchType;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;

        // Smart defaults
        this.branchStatus = BranchStatus.ACTIVE;
        this.country = "United States";
        this.openingTime = LocalTime.of(9, 0);   // 9:00 AM
        this.closingTime = LocalTime.of(17, 0);  // 5:00 PM
    }
    // Full constructor with management info
    public Branch(String branchName, String branchCode, BranchType branchType,
                  String streetAddress, String city, String state, String zipCode,
                  String phoneNumber, String email, String managingDirector) {
        this.branchName = branchName;
        this.branchCode = branchCode;
        this.branchType = branchType;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.managingDirector = managingDirector;
    }
}

