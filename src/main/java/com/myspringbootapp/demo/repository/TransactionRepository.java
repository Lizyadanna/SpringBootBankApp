package com.myspringbootapp.demo.repository;
import com.myspringbootapp.demo.entity.Account;
import com.myspringbootapp.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountId(Long accountId);     // Custom method
}
