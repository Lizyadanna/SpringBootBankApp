package com.myspringbootapp.demo.service;

import com.myspringbootapp.demo.entity.Branch;
import com.myspringbootapp.demo.repository.BranchRepository;
import com.myspringbootapp.demo.service.BranchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceTest {

    @Mock
    private BranchRepository branchRepository;  // Fake repository

    @InjectMocks
    private BranchService branchService;  // Real service with fake repository

    private Branch testBranch;

    @Before
    public void setUp() {
        testBranch = new Branch();
        testBranch.setId(1L);
        testBranch.setName("Test Branch");
    }

    @Test
    public void testAddBranch_Success() {
        // ARRANGE: Configure what the fake repository should return
        when(branchRepository.save(any(Branch.class))).thenReturn(testBranch);

        // ACT: Call the service method
        Branch inputBranch = new Branch();
        inputBranch.setName("New Branch");
        Branch result = branchService.createBranch(inputBranch);

        // ASSERT: Check the result
        assertNotNull("Result should not be null", result);
        assertEquals("Should return the saved branch", testBranch.getId(), result.getId());
        assertEquals("Should return the saved branch", testBranch.getName(), result.getName());

        // VERIFY: Make sure repository was called correctly
        verify(branchRepository, times(1)).save(inputBranch);
    }

    @Test
    public void testGetBranches_Success() {
        // ARRANGE
        Branch branch1 = new Branch();
        branch1.setId(1L);
        branch1.setName("Branch 1");

        List<Branch> expectedBranches = Arrays.asList(branch1);
        when(branchRepository.findAll()).thenReturn(expectedBranches);

        // ACT
        List<Branch> result = branchService.getBranches();

        // ASSERT
        assertNotNull("Result should not be null", result);
        assertEquals("Should return correct number of branches", 1, result.size());
        assertEquals("First branch should match", "Branch 1", result.get(0).getName());

        // VERIFY
        verify(branchRepository, times(1)).findAll();
    }


    @Test
    public void testGetBranchById_Found() {
        // ARRANGE
        when(branchRepository.findById(1L)).thenReturn(Optional.of(testBranch));

        // ACT
        Branch result = branchService.getBranchById(1L);

        // ASSERT
        assertNotNull("Result should not be null", result);
        assertEquals("Should return correct branch", testBranch.getId(), result.getId());
        assertEquals("Should return correct branch", testBranch.getName(), result.getName());

        // VERIFY
        verify(branchRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteBranch_Success() {
        // ACT
        branchService.deleteBranch(1L);

        // VERIFY
        verify(branchRepository, times(1)).deleteById(1L);
    }
}
