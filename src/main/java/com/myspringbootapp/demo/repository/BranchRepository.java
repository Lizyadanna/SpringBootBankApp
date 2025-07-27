package com.myspringbootapp.demo.repository;

import com.myspringbootapp.demo.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByBranchType(Branch.BranchType type);
}
