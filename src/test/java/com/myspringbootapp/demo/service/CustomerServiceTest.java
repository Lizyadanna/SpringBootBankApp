package com.myspringbootapp.demo.service;

import com.myspringbootapp.demo.entity.Customer;
import com.myspringbootapp.demo.repository.CustomerRepository;
import com.myspringbootapp.demo.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;  // Fake repository

    @InjectMocks
    private CustomerService customerService;  // Real service with fake repository

    private Customer testCustomer;

    @Before
    public void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setName("John Doe");
        testCustomer.setAccountType("Premium");
    }

    @Test
    public void testAddCustomer_Success() {
        // ARRANGE: Configure what the fake repository should return
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        // ACT: Call the service method
        Customer inputCustomer = new Customer();
        inputCustomer.setName("New Customer");
        inputCustomer.setAccountType("Basic");
        Customer result = customerService.addCustomer(inputCustomer);

        // ASSERT: Check the result
        assertNotNull("Result should not be null", result);
        assertEquals("Should return the saved customer", testCustomer.getId(), result.getId());
        assertEquals("Should return the saved customer", testCustomer.getName(), result.getName());
        assertEquals("Should return the saved customer", testCustomer.getAccountType(), result.getAccountType());

        // VERIFY: Make sure repository was called correctly
        verify(customerRepository, times(1)).save(inputCustomer);
    }

    @Test
    public void testGetCustomers_Success() {
        // ARRANGE: Create test data
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Alice Johnson");
        customer1.setAccountType("Premium");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Bob Smith");
        customer2.setAccountType("Basic");

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setName("Carol Wilson");
        customer3.setAccountType("Standard");

        List<Customer> expectedCustomers = Arrays.asList(customer1, customer2, customer3);

        // Configure mock repository
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        // ACT: Call the service method
        List<Customer> result = customerService.getCustomers();

        // ASSERT: Check the result
        assertNotNull("Result should not be null", result);
        assertEquals("Should return correct number of customers", 3, result.size());
        assertEquals("First customer should match", "Alice Johnson", result.get(0).getName());
        assertEquals("First customer account type should match", "Premium", result.get(0).getAccountType());
        assertEquals("Second customer should match", "Bob Smith", result.get(1).getName());
        assertEquals("Second customer account type should match", "Basic", result.get(1).getAccountType());
        assertEquals("Third customer should match", "Carol Wilson", result.get(2).getName());
        assertEquals("Third customer account type should match", "Standard", result.get(2).getAccountType());

        // VERIFY: Make sure repository was called
        verify(customerRepository, times(1)).findAll();
    }
}
