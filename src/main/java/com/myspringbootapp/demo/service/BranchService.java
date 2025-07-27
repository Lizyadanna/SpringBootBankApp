package com.myspringbootapp.demo.service;

import com.myspringbootapp.demo.entity.Branch;
import com.myspringbootapp.demo.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }
    public void updateBranch(Branch branch) {
        branchRepository.save(branch);
    }

    public List<Branch> getBranches(){
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElse(null);
    }

    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }
}
