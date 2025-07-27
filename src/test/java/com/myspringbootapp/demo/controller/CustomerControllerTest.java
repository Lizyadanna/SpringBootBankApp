package com.myspringbootapp.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myspringbootapp.demo.entity.Customer;
import com.myspringbootapp.demo.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateCustomer_Success() throws Exception {
        // ARRANGE: Set up test data
        Customer inputCustomer = new Customer();
        inputCustomer.setName("John Doe");
        inputCustomer.setAccountType("Premium");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setName("John Doe");
        savedCustomer.setAccountType("Premium");

        // Configure mock service
        when(customerService.addCustomer(any(Customer.class))).thenReturn(savedCustomer);

        // ACT & ASSERT: Send HTTP request and verify response
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputCustomer)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.accountType").value("Premium"));

        // VERIFY: Make sure service was called correctly
        verify(customerService, times(1)).addCustomer(any(Customer.class));
    }
}