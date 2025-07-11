package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
	

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee get(int id) {
        return employeeRepository.findById(id).get();
    }

    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> getAllPermanentEmployees() {
        return employeeRepository.getAllPermanentEmployees();
    }

    @Transactional
    public double getAverageSalary() {
        return employeeRepository.getAverageSalary();
    }

    @Transactional
    public double getAverageSalaryByDepartment(int deptId) {
        return employeeRepository.getAverageSalaryByDepartment(deptId);
    }
    public List<Employee> getAllEmployeesNative() {
        return employeeRepository.getAllEmployeesNative();
    } 

}
