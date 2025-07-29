package com.myspringbootapp.demo.service;

import com.myspringbootapp.demo.entity.Branch;
import com.myspringbootapp.demo.entity.Customer;
import com.myspringbootapp.demo.repository.BranchRepository;
import com.myspringbootapp.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BranchRepository branchRepository) {
        this.customerRepository = customerRepository;
        this.branchRepository = branchRepository;
    }

    public Customer createCustomer(Customer customer) {
            // Set default branch if none provided (for demo)
            if (customer.getHomeBranch() == null) {
                // Get the first branch or create a default one
                Branch defaultBranch = branchRepository.findById(1L)
                        .orElseThrow(() -> new RuntimeException("No branch found with ID 1"));
                customer.setHomeBranch(defaultBranch);
            }

            return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
