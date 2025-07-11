package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        updatedEmployee.setId(id);
        return employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> getEmployeesByDepartmentName(String deptName) {
        return employeeRepository.findEmployeesByDepartmentName(deptName);
    }
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    
    public Page<Employee> getAllEmployeesPaginated(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }



}
