package com.myspringbootapp.demo.controller;


import com.myspringbootapp.demo.entity.Employee;
import com.myspringbootapp.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    /**
     * REST Controller for managing Employee entities in the wealth management system
     */
    @RestController
    @RequestMapping("/api/employees")
    public class EmployeeController {

        @Autowired
        private EmployeeService employeeService; // Dependency injection

        /**
         * Create a new employee
         * POST /api/employees
         */
        @PostMapping
        public Employee createEmployee(@RequestBody Employee employee) {
            return employeeService.saveEmployee(employee);
        }

        /**
         * Get all employees
         * GET /api/employees
         */
        @GetMapping
        public List<Employee> getAllEmployees() {
            return employeeService.getAllEmployees();
        }

        /**
         * Get employee by ID
         * GET /api/employees/{id}
         */
        @GetMapping("/{id}")
        public Employee getEmployeeById(@PathVariable Long id) {
            return employeeService.getEmployeeById(id);
        }

        /**
         * Update an existing employee
         * PUT /api/employees/{id}
         */
        @PutMapping("/{id}")
        public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
            // Ensure the employee has the correct ID
            employee.setId(id);
            return employeeService.saveEmployee(employee);
        }

        /**
         * Delete an employee
         * DELETE /api/employees/{id}
         */
        @DeleteMapping("/{id}")
        public void deleteEmployee(@PathVariable Long id) {
            employeeService.deleteEmployee(id);
        }
}
