package com.myspringbootapp.demo.service;

import com.myspringbootapp.demo.entity.Account;
import com.myspringbootapp.demo.entity.Branch;
import com.myspringbootapp.demo.entity.Customer;
import com.myspringbootapp.demo.repository.AccountRepository;
import com.myspringbootapp.demo.repository.BranchRepository;
import com.myspringbootapp.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final BranchRepository branchRepository;


    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository, BranchRepository branchRepository) {
        this.customerRepository = customerRepository;
        this.branchRepository = branchRepository;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        // Generate account number
        account.setAccountNumber("ACC" + System.currentTimeMillis());

        // Set default customer (first customer)
        if (account.getCustomer() == null) {
            Customer customer = customerRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("No customer found with ID 1"));
            account.setCustomer(customer);
        }

        // Set default branch (first branch)
        if (account.getBranch() == null) {
            Branch branch = branchRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("No branch found with ID 1"));
            account.setBranch(branch);
        }

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}
