package com.example.rest;

import com.example.rest.service.EmployeeService;
import com.example.rest.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class EmployeeManagementSystemApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeService employeeService = context.getBean(EmployeeService.class);
        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee e : employees) {
            System.out.println(e.getId() + " - " + e.getName());
        }
    }
}
