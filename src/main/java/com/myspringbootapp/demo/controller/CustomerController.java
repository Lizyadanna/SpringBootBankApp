package com.myspringbootapp.demo.controller;

import com.myspringbootapp.demo.entity.Customer;
import com.myspringbootapp.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Customer entities in the wealth management system
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService; // Dependency injection

    /**
     * Create a new customer
     * POST /api/customers
     */
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    /**
     * Get all customers
     * GET /api/customers
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    /**
     * Get customer by ID
     * GET /api/customers/{id}
     */
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    /**
     * Update an existing customer
     * PUT /api/customers/{id}
     */
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        // Ensure the customer has the correct ID
        customer.setId(id);
        return customerService.updateCustomer(customer);
    }

    /**
     * Delete a customer
     * DELETE /api/customers/{id}
     */
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}