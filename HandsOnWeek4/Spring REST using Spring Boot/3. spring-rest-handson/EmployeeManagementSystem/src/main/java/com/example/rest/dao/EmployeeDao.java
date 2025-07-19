package com.example.rest.dao;

import com.example.rest.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {
    public static List<Employee> EMPLOYEE_LIST;

    @Autowired
    public EmployeeDao(@Qualifier("employeeList") List<Employee> list) {
        EMPLOYEE_LIST = list;
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }
}
