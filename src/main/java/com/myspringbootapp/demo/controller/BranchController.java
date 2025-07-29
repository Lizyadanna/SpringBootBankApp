package com.myspringbootapp.demo.controller;

import com.myspringbootapp.demo.entity.Branch;
import com.myspringbootapp.demo.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Branch entities in the wealth management system
 */
@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService; // Dependency injection

    /**
     * Create a new branch
     * POST /api/branches
     */
    @PostMapping
    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.createBranch(branch);
    }

    /**
     * Get all branches
     * GET /api/branches
     */
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getBranches();
    }

    /**
     * Get branch by ID
     * GET /api/branches/{id}
     */
    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable Long id) {
        return branchService.getBranchById(id);
    }

    /**
     * Update an existing branch
     * PUT /api/branches/{id}
     */
    @PutMapping("/{id}")
    public Branch updateBranch(@PathVariable Long id, @RequestBody Branch branch) {
        // Ensure the branch has the correct ID
        branch.setId(id);
        return branchService.updateBranch(branch);
    }

    /**
     * Delete a branch
     * DELETE /api/branches/{id}
     */
    @DeleteMapping("/{id}")
    public void deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
    }
}