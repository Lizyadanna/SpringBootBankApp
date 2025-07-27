package com.myspringbootapp.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myspringbootapp.demo.entity.Branch;
import com.myspringbootapp.demo.service.BranchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class) // JUnit 4 + Mockito integration
public class BranchControllerTest{

    private MockMvc mockMvc; // Simulates HTTP requests
    private ObjectMapper objectMapper; // Converts objects to JSON


    @Mock
    private BranchService branchService; // Fake service

    // The actual controller being tested
    @InjectMocks
    private BranchController branchController; // Real controller  with fake service

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(branchController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateBranch_Success() throws Exception {
        // ARRANGE: Set up test scenario
        // What we're sending to the controller
        Branch inputBranch = new Branch();
        inputBranch.setName("Test Branch");

        // What we want the fake service to return
        Branch savedBranch = new Branch();
        savedBranch.setId(1L);
        savedBranch.setName("Test Branch");

        // CONFIGURE FAKE SERVICE: "When addBranch is called, return savedBranch"
        when(branchService.createBranch(any(Branch.class))).thenReturn(savedBranch);

        // ACT & ASSERT: Send HTTP request and verify response
        mockMvc.perform(post("/api/branches")
                        .contentType(MediaType.APPLICATION_JSON)  // Tell controller: "I'm sending JSON"
                        .content(objectMapper.writeValueAsString(inputBranch)))  // Convert object to JSON

                // VERIFY HTTP RESPONSE
                .andExpect(status().isOk())  // HTTP 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Response is JSON
                .andExpect(jsonPath("$.id").value(1L))  // Response has correct ID
                .andExpect(jsonPath("$.name").value("Test Branch"));  // Response has correct name

        // VERIFY SERVICE INTERACTION: Make sure controller called service correctly
        verify(branchService, times(1)).createBranch(any(Branch.class));
    }
}
