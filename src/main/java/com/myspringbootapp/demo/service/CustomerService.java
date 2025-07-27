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
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElse(new Customer());
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

}
