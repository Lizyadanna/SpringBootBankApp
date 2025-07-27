package com.myspringbootapp.demo.controller;

import com.myspringbootapp.demo.entity.Branch;
import com.myspringbootapp.demo.service.BranchService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Rest Controller for managing branch entities
 *
 */
@RestController
@RequestMapping("api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService; //Dependency injection

    @PostMapping
    public Branch createBranch(@RequestBody Branch branch) {
         return branchService.createBranch(branch);
    }

    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getBranches();
    }

 // Create a function to get a branch by ID
    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable long id) {
        return branchService.getBranchById(id);
    }

}
