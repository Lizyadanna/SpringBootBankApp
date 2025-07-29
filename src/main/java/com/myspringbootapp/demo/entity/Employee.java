package com.myspringbootapp.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Email;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false)
    @Email
    private String email;

    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

    @Column(name="job_title")
    private String jobTitle;

    @Column(name="department")
    private String department;

    @Column(name="hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name="salary")
    private BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = true)
    @JsonIgnore
    private Branch branch;


    // Custom constructor
    public Employee() {
        this.hireDate = LocalDate.now();  // Auto-set hire date to today
    }
    // Custom constructor with all fields
    public Employee(String firstName, String lastName, String email, String phoneNumber, String jobTitle, String department, LocalDate hireDate, BigDecimal salary, Branch branch) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
        this.department = department;
        this.hireDate = hireDate;
        this.salary = salary;
        this.branch = branch;
    }

    // Overridden toString() method because of lazy loading of branch entity
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate=" + hireDate +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                ", branchId=" + (branch != null ? branch.getId() : "null") +
                '}';
    }

}
