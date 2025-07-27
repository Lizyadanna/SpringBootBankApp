package com.myspringbootapp.demo.service;

import com.myspringbootapp.demo.entity.Employee;
import com.myspringbootapp.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    // Constructor injection because it's considered more professional and testable.
    private final EmployeeRepository employeeRepository; // Dependency declaration

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) { // Spring injects it
        this.employeeRepository = employeeRepository; // Store reference
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}
